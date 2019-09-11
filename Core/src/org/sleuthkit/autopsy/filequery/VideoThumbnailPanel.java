/*
 * Autopsy
 *
 * Copyright 2019 Basis Technology Corp.
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
package org.sleuthkit.autopsy.filequery;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Class which displays thumbnails and information for a video file.
 */
public final class VideoThumbnailPanel extends javax.swing.JPanel implements ListCellRenderer<VideoThumbnailsWrapper> {

    private static final int GAP_SIZE = 4;
    private static final Color SELECTION_COLOR = new Color(100, 200, 255);
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form VideoThumbnailPanel
     */
    public VideoThumbnailPanel() {
        initComponents();
        this.setFocusable(true);
    }

    /**
     * Add the thumbnails to the panel.
     *
     * @param thumbnailWrapper
     */
    private void addThumbnails(VideoThumbnailsWrapper thumbnailWrapper) {
        imagePanel.removeAll();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        imagePanel.add(new javax.swing.Box.Filler(new java.awt.Dimension(GAP_SIZE, 0), new java.awt.Dimension(GAP_SIZE, 0), new java.awt.Dimension(GAP_SIZE, 32767)), gridBagConstraints);
        gridBagConstraints.gridy = 1;
        imagePanel.add(new javax.swing.Box.Filler(new java.awt.Dimension(GAP_SIZE, 0), new java.awt.Dimension(GAP_SIZE, 0), new java.awt.Dimension(GAP_SIZE, 32767)), gridBagConstraints);
        gridBagConstraints.gridx++;
        int timeIndex = 0;
        int[] timeStamps = thumbnailWrapper.getTimeStamps();
        for (Image image : thumbnailWrapper.getThumbnails()) {
            gridBagConstraints.gridy = 0;
            imagePanel.add(new JLabel(new ImageIcon(image)), gridBagConstraints);
            gridBagConstraints.gridy = 1;
            long millis = timeStamps[timeIndex];
            long hours = TimeUnit.MILLISECONDS.toHours(millis);
            millis -= TimeUnit.HOURS.toMillis(hours);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
            millis -= TimeUnit.MINUTES.toMillis(minutes);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
            imagePanel.add(new JLabel(String.format("%01d:%02d:%02d", hours, minutes, seconds)), gridBagConstraints);
            gridBagConstraints.gridx++;
            gridBagConstraints.gridy = 0;
            imagePanel.add(new javax.swing.Box.Filler(new java.awt.Dimension(GAP_SIZE, 0), new java.awt.Dimension(GAP_SIZE, 0), new java.awt.Dimension(GAP_SIZE, 32767)), gridBagConstraints);
            gridBagConstraints.gridy = 1;
            imagePanel.add(new javax.swing.Box.Filler(new java.awt.Dimension(GAP_SIZE, 0), new java.awt.Dimension(GAP_SIZE, 0), new java.awt.Dimension(GAP_SIZE, 32767)), gridBagConstraints);
            gridBagConstraints.gridx++;
            timeIndex++;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagePanel = new javax.swing.JPanel();
        fileInfoLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        imagePanel.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addComponent(fileInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fileInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fileInfoLabel;
    private javax.swing.JPanel imagePanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList<? extends VideoThumbnailsWrapper> list, VideoThumbnailsWrapper value, int index, boolean isSelected, boolean cellHasFocus) {
        fileInfoLabel.setText(value.getFilePath());
        addThumbnails(value);
        imagePanel.setBackground(isSelected ? SELECTION_COLOR : list.getBackground());
        setBackground(isSelected ? SELECTION_COLOR : list.getBackground());
        return this;
    }
}
