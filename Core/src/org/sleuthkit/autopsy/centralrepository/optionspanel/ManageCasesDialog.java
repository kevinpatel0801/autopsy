/*
 * Central Repository
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
package org.sleuthkit.autopsy.centralrepository.optionspanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.centralrepository.datamodel.CorrelationCase;
import org.sleuthkit.autopsy.centralrepository.datamodel.CorrelationDataSource;
import org.sleuthkit.autopsy.centralrepository.datamodel.CentralRepoException;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.openide.util.NbBundle.Messages;
import org.sleuthkit.autopsy.centralrepository.datamodel.CentralRepository;

/**
 * A dialog which displays cases existing in the central repository and the
 * central repo information associated with them.
 *
 */
final class ManageCasesDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;
    private final CasesTableModel casesTableModel = new CasesTableModel();
    private final DataSourcesTableModel dataSourcesTableModel = new DataSourcesTableModel();
    private final static Logger logger = Logger.getLogger(ManageCasesDialog.class.getName());

    /**
     * Creates new form ManageCasesDialog
     */
    @SuppressWarnings("PMD.SingularField") // UI widgets cause lots of false positives
    @Messages({"ManageCasesDialog.title.text=Manage Cases"})
    private ManageCasesDialog() {
        super(WindowManager.getDefault().getMainWindow(), Bundle.ManageCasesDialog_title_text(),
                true);
        initComponents();
        try {
            CentralRepository dbManager = CentralRepository.getInstance();
            Map<Integer, List<CorrelationDataSource>> dataSourcesByCaseId = new HashMap<>();
            for (CorrelationDataSource dataSource : dbManager.getDataSources()) {
                int caseID = dataSource.getCaseID();
                List<CorrelationDataSource> dataSourceNames = dataSourcesByCaseId.getOrDefault(caseID, new ArrayList<>());
                dataSourceNames.add(dataSource);
                dataSourcesByCaseId.put(caseID, dataSourceNames);
            }
            for (CorrelationCase eamCase : dbManager.getCases()) {
                casesTableModel.addEamCase(eamCase, dataSourcesByCaseId.getOrDefault(eamCase.getID(), new ArrayList<>()));
            }
        } catch (CentralRepoException ex) {
            logger.log(Level.SEVERE, "Error getting list of cases from database.", ex); // NON-NLS
        }

        casesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    updateSelection();
                }
            }
        });
        //sort on first column by default
        casesTable.getRowSorter().toggleSortOrder(0);
    }

    /**
     * Create and display the Manage Cases dialog for the currently enabled
     * central repository.
     */
    static void displayManageCasesDialog() {
        ManageCasesDialog caseInfoDialog = new ManageCasesDialog();
        caseInfoDialog.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        caseInfoDialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        casesSplitPane = new javax.swing.JSplitPane();
        caseInfoPanel = new javax.swing.JPanel();
        dataSourcesScrollPane = new javax.swing.JScrollPane();
        dataSourcesTable = new javax.swing.JTable();
        notesScrollPane = new javax.swing.JScrollPane();
        notesTextArea = new javax.swing.JTextArea();
        caseInfoLabel = new javax.swing.JLabel();
        dataSourcesLabel = new javax.swing.JLabel();
        notesLabel = new javax.swing.JLabel();
        orgLabel = new javax.swing.JLabel();
        caseNumberLabel = new javax.swing.JLabel();
        examinerEmailLabel = new javax.swing.JLabel();
        examinerNameLabel = new javax.swing.JLabel();
        examinerPhoneLabel = new javax.swing.JLabel();
        orgValueLabel = new javax.swing.JLabel();
        caseNumberValueLabel = new javax.swing.JLabel();
        examinerNameValueLabel = new javax.swing.JLabel();
        examinerEmailValueLabel = new javax.swing.JLabel();
        examinerPhoneValueLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        casesPanel = new javax.swing.JPanel();
        casesScrollPane = new javax.swing.JScrollPane();
        casesTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 400));

        casesSplitPane.setDividerLocation(380);

        dataSourcesTable.setAutoCreateRowSorter(true);
        dataSourcesTable.setModel(dataSourcesTableModel);
        dataSourcesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dataSourcesScrollPane.setViewportView(dataSourcesTable);

        notesScrollPane.setBorder(null);

        notesTextArea.setEditable(false);
        notesTextArea.setBackground(new java.awt.Color(240, 240, 240));
        notesTextArea.setColumns(20);
        notesTextArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        notesTextArea.setLineWrap(true);
        notesTextArea.setRows(3);
        notesTextArea.setWrapStyleWord(true);
        notesTextArea.setBorder(null);
        notesScrollPane.setViewportView(notesTextArea);

        org.openide.awt.Mnemonics.setLocalizedText(caseInfoLabel, org.openide.util.NbBundle.getMessage(ManageCasesDialog.class, "ManageCasesDialog.caseInfoLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(dataSourcesLabel, org.openide.util.NbBundle.getMessage(ManageCasesDialog.class, "ManageCasesDialog.dataSourcesLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(notesLabel, org.openide.util.NbBundle.getMessage(ManageCasesDialog.class, "ManageCasesDialog.notesLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(orgLabel, org.openide.util.NbBundle.getMessage(ManageCasesDialog.class, "ManageCasesDialog.orgLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(caseNumberLabel, org.openide.util.NbBundle.getMessage(ManageCasesDialog.class, "ManageCasesDialog.caseNumberLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(examinerEmailLabel, org.openide.util.NbBundle.getMessage(ManageCasesDialog.class, "ManageCasesDialog.examinerEmailLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(examinerNameLabel, org.openide.util.NbBundle.getMessage(ManageCasesDialog.class, "ManageCasesDialog.examinerNameLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(examinerPhoneLabel, org.openide.util.NbBundle.getMessage(ManageCasesDialog.class, "ManageCasesDialog.examinerPhoneLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(closeButton, org.openide.util.NbBundle.getMessage(ManageCasesDialog.class, "ManageCasesDialog.closeButton.text")); // NOI18N
        closeButton.setMaximumSize(new java.awt.Dimension(65, 23));
        closeButton.setMinimumSize(new java.awt.Dimension(65, 23));
        closeButton.setPreferredSize(new java.awt.Dimension(65, 23));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout caseInfoPanelLayout = new javax.swing.GroupLayout(caseInfoPanel);
        caseInfoPanel.setLayout(caseInfoPanelLayout);
        caseInfoPanelLayout.setHorizontalGroup(
            caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caseInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(caseInfoPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(dataSourcesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(caseInfoPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(caseInfoPanelLayout.createSequentialGroup()
                                .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(orgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(caseNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(examinerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(examinerEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(examinerPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(caseInfoPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(caseNumberValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(orgValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(caseInfoPanelLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(examinerNameValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(examinerEmailValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(examinerPhoneValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(notesLabel)
                            .addGroup(caseInfoPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(notesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, caseInfoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(caseInfoPanelLayout.createSequentialGroup()
                        .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(caseInfoLabel)
                            .addComponent(dataSourcesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        caseInfoPanelLayout.setVerticalGroup(
            caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, caseInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(caseInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(caseInfoPanelLayout.createSequentialGroup()
                        .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(caseInfoPanelLayout.createSequentialGroup()
                                .addComponent(orgLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(caseNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(caseNumberValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(examinerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(examinerNameValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(orgValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(examinerEmailLabel))
                    .addComponent(examinerEmailValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(caseInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(examinerPhoneLabel)
                    .addComponent(examinerPhoneValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataSourcesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataSourcesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        casesSplitPane.setRightComponent(caseInfoPanel);

        casesTable.setAutoCreateRowSorter(true);
        casesTable.setModel(casesTableModel);
        casesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        casesScrollPane.setViewportView(casesTable);

        javax.swing.GroupLayout casesPanelLayout = new javax.swing.GroupLayout(casesPanel);
        casesPanel.setLayout(casesPanelLayout);
        casesPanelLayout.setHorizontalGroup(
            casesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(casesPanelLayout.createSequentialGroup()
                .addComponent(casesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        casesPanelLayout.setVerticalGroup(
            casesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(casesPanelLayout.createSequentialGroup()
                .addComponent(casesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        casesSplitPane.setLeftComponent(casesPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(casesSplitPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(casesSplitPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * Update the information displayed to reflect the currently selected case.
     */
    private void updateSelection() {
        dataSourcesTableModel.clearTable();
        if (casesTable.getSelectedRow() >= 0 && casesTable.getSelectedRow() < casesTable.getRowCount()) {
            CaseDataSourcesWrapper caseWrapper = casesTableModel.getEamCase(casesTable.convertRowIndexToModel(casesTable.getSelectedRow()));
            orgValueLabel.setText(caseWrapper.getOrganizationName());
            caseNumberValueLabel.setText(caseWrapper.getCaseNumber());
            examinerNameValueLabel.setText(caseWrapper.getExaminerName());
            examinerPhoneValueLabel.setText(caseWrapper.getExaminerPhone());
            examinerEmailValueLabel.setText(caseWrapper.getExaminerEmail());
            notesTextArea.setText(caseWrapper.getNotes());
            dataSourcesTableModel.addDataSources(caseWrapper.getDataSources());
        } else {
            orgValueLabel.setText("");
            caseNumberValueLabel.setText("");
            examinerNameValueLabel.setText("");
            examinerPhoneValueLabel.setText("");
            examinerEmailValueLabel.setText("");
            notesTextArea.setText("");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel caseInfoLabel;
    private javax.swing.JPanel caseInfoPanel;
    private javax.swing.JLabel caseNumberLabel;
    private javax.swing.JLabel caseNumberValueLabel;
    private javax.swing.JPanel casesPanel;
    private javax.swing.JScrollPane casesScrollPane;
    private javax.swing.JSplitPane casesSplitPane;
    private javax.swing.JTable casesTable;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel dataSourcesLabel;
    private javax.swing.JScrollPane dataSourcesScrollPane;
    private javax.swing.JTable dataSourcesTable;
    private javax.swing.JLabel examinerEmailLabel;
    private javax.swing.JLabel examinerEmailValueLabel;
    private javax.swing.JLabel examinerNameLabel;
    private javax.swing.JLabel examinerNameValueLabel;
    private javax.swing.JLabel examinerPhoneLabel;
    private javax.swing.JLabel examinerPhoneValueLabel;
    private javax.swing.JLabel notesLabel;
    private javax.swing.JScrollPane notesScrollPane;
    private javax.swing.JTextArea notesTextArea;
    private javax.swing.JLabel orgLabel;
    private javax.swing.JLabel orgValueLabel;
    // End of variables declaration//GEN-END:variables
}
