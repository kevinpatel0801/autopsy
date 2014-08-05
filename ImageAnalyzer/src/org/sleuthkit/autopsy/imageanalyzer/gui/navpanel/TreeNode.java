/*
 * Autopsy Forensic Browser
 *
 * Copyright 2013 Basis Technology Corp.
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
package org.sleuthkit.autopsy.imageanalyzer.gui.navpanel;

import org.sleuthkit.autopsy.imageanalyzer.grouping.Grouping;

/**
 *
 */
 class TreeNode {

   private  String path;
   private Grouping group;

    public String getPath() {
        return path;
    }

    public Grouping getGroup() {
        return group;
    }

    public TreeNode(String path, Grouping group) {
        this.path = path;
        this.group = group;
    }

    void setGroup(Grouping g) {
    group = g;
    }
}
