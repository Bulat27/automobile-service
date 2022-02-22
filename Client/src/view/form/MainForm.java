/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.controller.MainFormController;

/**
 *
 * @author Dragon
 */
public class MainForm extends javax.swing.JFrame {

    private MainFormController mainFormController;

    /**
     * Creates new form MainForm
     *
     * @param mainFormController
     */
    public MainForm(MainFormController mainFormController) {
        initComponents();
        this.mainFormController = mainFormController;
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//TODO: You can add a listener and make it do the same as log out
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogOut = new javax.swing.JButton();
        menuBarMain = new javax.swing.JMenuBar();
        menuEmployee = new javax.swing.JMenu();
        menuItemAddEmployee = new javax.swing.JMenuItem();
        menuItemSearchEmployees = new javax.swing.JMenuItem();
        menuServiceBook = new javax.swing.JMenu();
        menuItemAddServiceBook = new javax.swing.JMenuItem();
        menuItemSearchServiceBooks = new javax.swing.JMenuItem();
        menuService = new javax.swing.JMenu();
        menuItemAddService = new javax.swing.JMenuItem();
        menuItemSearchServices = new javax.swing.JMenuItem();
        menuRepair = new javax.swing.JMenu();
        menuItemAddRepair = new javax.swing.JMenuItem();
        menuItemSearchRepair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLogOut.setText("Log out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        menuEmployee.setText("Employee");

        menuItemAddEmployee.setText("Add");
        menuEmployee.add(menuItemAddEmployee);

        menuItemSearchEmployees.setText("Search");
        menuEmployee.add(menuItemSearchEmployees);

        menuBarMain.add(menuEmployee);

        menuServiceBook.setText("Service book");

        menuItemAddServiceBook.setText("Add");
        menuServiceBook.add(menuItemAddServiceBook);

        menuItemSearchServiceBooks.setText("Search");
        menuServiceBook.add(menuItemSearchServiceBooks);

        menuBarMain.add(menuServiceBook);

        menuService.setText("Service");

        menuItemAddService.setText("Add");
        menuItemAddService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAddServiceActionPerformed(evt);
            }
        });
        menuService.add(menuItemAddService);

        menuItemSearchServices.setText("Search");
        menuItemSearchServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSearchServicesActionPerformed(evt);
            }
        });
        menuService.add(menuItemSearchServices);

        menuBarMain.add(menuService);

        menuRepair.setText("Repair");

        menuItemAddRepair.setText("Add");
        menuRepair.add(menuItemAddRepair);

        menuItemSearchRepair.setText("Search");
        menuRepair.add(menuItemSearchRepair);

        menuBarMain.add(menuRepair);

        setJMenuBar(menuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(303, Short.MAX_VALUE)
                .addComponent(btnLogOut)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
//        try {
//            Controller.getInstance().logOut();
//        } catch (Exception ex) {
//            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);  
//        }
//        this.dispose();//Dispose even if there is an error on the server side. The client doesn't care about that!
        mainFormController.closeForm();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void menuItemAddServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAddServiceActionPerformed
        mainFormController.openAddServiceForm();
    }//GEN-LAST:event_menuItemAddServiceActionPerformed

    private void menuItemSearchServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSearchServicesActionPerformed
        try {
            mainFormController.openShowServicesForm();
        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error opening show services form: " + ex.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuItemSearchServicesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogOut;
    private javax.swing.JMenuBar menuBarMain;
    private javax.swing.JMenu menuEmployee;
    private javax.swing.JMenuItem menuItemAddEmployee;
    private javax.swing.JMenuItem menuItemAddRepair;
    private javax.swing.JMenuItem menuItemAddService;
    private javax.swing.JMenuItem menuItemAddServiceBook;
    private javax.swing.JMenuItem menuItemSearchEmployees;
    private javax.swing.JMenuItem menuItemSearchRepair;
    private javax.swing.JMenuItem menuItemSearchServiceBooks;
    private javax.swing.JMenuItem menuItemSearchServices;
    private javax.swing.JMenu menuRepair;
    private javax.swing.JMenu menuService;
    private javax.swing.JMenu menuServiceBook;
    // End of variables declaration//GEN-END:variables
}
