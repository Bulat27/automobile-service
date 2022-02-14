/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form;

import controller.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Dragon
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        toggleStartStopButtons(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblServerStatus = new javax.swing.JLabel();
        lblServerStatusText = new javax.swing.JLabel();
        btnStartServer = new javax.swing.JButton();
        btnStopServer = new javax.swing.JButton();
        lblAuthenticatedEmployees = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        menuBarMain = new javax.swing.JMenuBar();
        menuConfiguration = new javax.swing.JMenu();
        menuItemServerConfiguration = new javax.swing.JMenuItem();
        menuItemDatabaseConfiguration = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        lblServerStatus.setText("Server status:");

        lblServerStatusText.setText("Server is stopped!");

        btnStartServer.setText("Start server");
        btnStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartServerActionPerformed(evt);
            }
        });

        btnStopServer.setText("Stop server");
        btnStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopServerActionPerformed(evt);
            }
        });

        lblAuthenticatedEmployees.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAuthenticatedEmployees.setText("Authenticated employees:");

        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblEmployees);

        menuConfiguration.setText("Configuration");

        menuItemServerConfiguration.setText("Server configuration");
        menuConfiguration.add(menuItemServerConfiguration);

        menuItemDatabaseConfiguration.setText("Database configuration");
        menuConfiguration.add(menuItemDatabaseConfiguration);

        menuBarMain.add(menuConfiguration);

        setJMenuBar(menuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStartServer)
                    .addComponent(lblServerStatus))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblServerStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStopServer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblAuthenticatedEmployees)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblServerStatus)
                    .addComponent(lblServerStatusText))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartServer)
                    .addComponent(btnStopServer))
                .addGap(56, 56, 56)
                .addComponent(lblAuthenticatedEmployees)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartServerActionPerformed
        try {
            Controller.getInstance().startServer();
            toggleStartStopButtons(false);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Start server error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnStartServerActionPerformed

    private void btnStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopServerActionPerformed
        try {
            Controller.getInstance().stopServer();
            toggleStartStopButtons(true);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage(), "Stop server error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnStopServerActionPerformed

    private void toggleStartStopButtons(boolean signal){
        btnStartServer.setEnabled(signal);
        btnStopServer.setEnabled(!signal);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartServer;
    private javax.swing.JButton btnStopServer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAuthenticatedEmployees;
    private javax.swing.JLabel lblServerStatus;
    private javax.swing.JLabel lblServerStatusText;
    private javax.swing.JMenuBar menuBarMain;
    private javax.swing.JMenu menuConfiguration;
    private javax.swing.JMenuItem menuItemDatabaseConfiguration;
    private javax.swing.JMenuItem menuItemServerConfiguration;
    private javax.swing.JTable tblEmployees;
    // End of variables declaration//GEN-END:variables
}