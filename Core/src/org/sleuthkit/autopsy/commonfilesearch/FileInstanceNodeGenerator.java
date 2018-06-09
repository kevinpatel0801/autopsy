/*
 * 
 * Autopsy Forensic Browser
 * 
 * Copyright 2018 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.commonfilesearch;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import org.openide.util.Exceptions;
import org.sleuthkit.autopsy.casemodule.Case;
import org.sleuthkit.autopsy.casemodule.NoCurrentCaseException;
import org.sleuthkit.autopsy.centralrepository.datamodel.CentralRepositoryFile;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.autopsy.datamodel.CentralRepositoryFileInstanceNode;
import org.sleuthkit.autopsy.datamodel.DisplayableItemNode;
import org.sleuthkit.autopsy.datamodel.SleuthkitCaseFileInstanceNode;
import org.sleuthkit.datamodel.AbstractFile;
import org.sleuthkit.datamodel.SleuthkitCase;
import org.sleuthkit.datamodel.TskCoreException;

/**
 * Defines types which can be used to get some sort of File Instance node (a
 * child of the MD5Node) for use in the common files tree table.
 */
public abstract class FileInstanceNodeGenerator {

    private static final Logger LOGGER = Logger.getLogger(FileInstanceNodeGenerator.class.getName());
    protected Long abstractFileReference;
    protected Map<Long, AbstractFile> cachedFiles;
    private String dataSource;

    public FileInstanceNodeGenerator(Long abstractFileReference, Map<Long, AbstractFile> cachedFiles, String dataSource) {
        this.abstractFileReference = abstractFileReference;
        this.cachedFiles = cachedFiles;
        this.dataSource = dataSource;
    }

    /**
     * Grab a cached instance of the AbstractFile or grab one from the
     * SleuthkitCase. Use this in implementations of <code>generateNode</code>.
     *
     * @param objectId
     * @param cachedFiles
     * @return AbstractFile which is identical to the file instance generated by
     * implementations of this object
     */
    protected AbstractFile lookupOrCreateAbstractFile() {
        if (this.cachedFiles.containsKey(this.abstractFileReference)) {
            return this.cachedFiles.get(this.abstractFileReference);
        } else {
            AbstractFile file = FileInstanceNodeGenerator.loadFileFromSleuthkitCase(this.abstractFileReference);
            this.cachedFiles.put(this.abstractFileReference, file);
            return file;
        }
    }

    private static AbstractFile loadFileFromSleuthkitCase(Long objectId) {

        Case currentCase;
        try {
            currentCase = Case.getCurrentCaseThrows();

            SleuthkitCase tskDb = currentCase.getSleuthkitCase();

            AbstractFile abstractFile = tskDb.findAllFilesWhere(String.format("obj_id in (%s)", objectId)).get(0);

            return abstractFile;

        } catch (TskCoreException | NoCurrentCaseException ex) {
            LOGGER.log(Level.SEVERE, String.format("Unable to find AbstractFile for record with obj_id: %s.  Node not created.", new Object[]{objectId}), ex);
            return null;
        }
    }

    private static AbstractFile lookupOrCreateAbstractFile(Long objectId, Map<Long, AbstractFile> cachedFiles) {
        if (cachedFiles.containsKey(objectId)) {
            return cachedFiles.get(objectId);
        } else {
            AbstractFile file = FileInstanceNodeGenerator.loadFileFromSleuthkitCase(objectId);
            cachedFiles.put(objectId, file);
            return file;
        }
    }

    /**
     * Create a node which is a child of the MD5Node, to be used to display a
     * row in the tree table
     *
     * @return child row node
     */
    public abstract DisplayableItemNode generateNode();

    /**
     *
     * @param dataSource
     */
    protected void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Get string name of the data source where this instance appears.
     *
     * @return
     */
    public String getDataSource() {

        /**
         * Even though we could get this from the CR record or the AbstractFile,
         * we want to avoid getting it from the AbstractFile because it would be
         * an extra database roundtrip.
         */
        return this.dataSource;
    }

    public Long getIdenticalFileSleuthkitCaseObjectID() {
        return this.abstractFileReference;
    }

    public static FileInstanceNodeGenerator createInstance(Iterator<FileInstanceNodeGenerator> identicalFileNodeGeneratorIterator, CentralRepositoryFile instance, Map<Long, AbstractFile> cachedFiles) throws Exception {

        Long arbitraryIdenticalAbstractFileId = null;

        final String instanceDataSource = instance.getCorrelationDataSource().getName().toLowerCase();
        final String instanceCase = instance.getCorrelationCase().getDisplayName().toLowerCase();
        final String instancePath = instance.getFilePath().toLowerCase();

        while (identicalFileNodeGeneratorIterator.hasNext()) {

            FileInstanceNodeGenerator identicalFileNodeGenerator = identicalFileNodeGeneratorIterator.next();

            final Long identicalFileSleuthkitCaseObjectID = identicalFileNodeGenerator.getIdenticalFileSleuthkitCaseObjectID();

            final AbstractFile referenceFile = FileInstanceNodeGenerator.lookupOrCreateAbstractFile(identicalFileSleuthkitCaseObjectID, cachedFiles);
            final Long referenceFileId = referenceFile.getId();
            arbitraryIdenticalAbstractFileId = referenceFileId;

            final String referenceFileDataSource = identicalFileNodeGenerator.getDataSource().toLowerCase();

            final String referenceCase = Case.getCurrentCase().getDisplayName().toLowerCase();

            final String referencePath = Paths.get(referenceFile.getParentPath(), referenceFile.getName()).toString().toLowerCase().replace("\\", "/");

            final boolean sameDataSource = referenceFileDataSource.equals(instanceDataSource);
            final boolean sameCase = referenceCase.equals(instanceCase);
            final boolean samePathAndName = referencePath.equals(instancePath);

            if (sameDataSource && sameCase && samePathAndName) {
                String dataSource = String.format("%s: %s", referenceCase, referenceFileDataSource);
                return new SleuthkitCaseFileInstanceMetadata(referenceFile.getId(), cachedFiles, dataSource);
            }
        }

        if (arbitraryIdenticalAbstractFileId != null) {
            String dataSource = String.format("%s: %s", instanceCase, instanceDataSource);
            return new CentralRepositoryCaseFileInstanceMetadata(instance, arbitraryIdenticalAbstractFileId, cachedFiles, dataSource);
        } else {
            throw new Exception("Unable to get instance.");
        }
    }
}
