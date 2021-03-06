/*
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
package org.sleuthkit.autopsy.modules.photoreccarver;

import org.sleuthkit.autopsy.ingest.IngestModuleIngestJobSettings;
import org.sleuthkit.autopsy.ingest.IngestModuleIngestJobSettingsPanel;

/**
 * Ingest job settings panel for the Encryption Detection module.
 */
@SuppressWarnings("PMD.SingularField") // UI widgets cause lots of false positives
final class PhotoRecCarverIngestJobSettingsPanel extends IngestModuleIngestJobSettingsPanel {

    /**
     * Instantiate the ingest job settings panel.
     *
     * @param settings The ingest job settings.
     */
    public PhotoRecCarverIngestJobSettingsPanel(PhotoRecCarverIngestJobSettings settings) {
        initComponents();
        customizeComponents(settings);
    }

    /**
     * Update components with values from the ingest job settings.
     *
     * @param settings The ingest job settings.
     */
    private void customizeComponents(PhotoRecCarverIngestJobSettings settings) {
        keepCorruptedFilesCheckbox.setSelected(settings.isKeepCorruptedFiles());
    }

    @Override
    public IngestModuleIngestJobSettings getSettings() {
        return new PhotoRecCarverIngestJobSettings(
                keepCorruptedFilesCheckbox.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        keepCorruptedFilesCheckbox = new javax.swing.JCheckBox();
        detectionSettingsLabel = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(keepCorruptedFilesCheckbox, org.openide.util.NbBundle.getMessage(PhotoRecCarverIngestJobSettingsPanel.class, "PhotoRecCarverIngestJobSettingsPanel.keepCorruptedFilesCheckbox.text")); // NOI18N

        detectionSettingsLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(detectionSettingsLabel, org.openide.util.NbBundle.getMessage(PhotoRecCarverIngestJobSettingsPanel.class, "PhotoRecCarverIngestJobSettingsPanel.detectionSettingsLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(keepCorruptedFilesCheckbox))
                    .addComponent(detectionSettingsLabel))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(detectionSettingsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(keepCorruptedFilesCheckbox)
                .addContainerGap(145, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel detectionSettingsLabel;
    private javax.swing.JCheckBox keepCorruptedFilesCheckbox;
    // End of variables declaration//GEN-END:variables
}
