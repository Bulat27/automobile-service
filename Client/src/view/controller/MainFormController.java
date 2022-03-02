/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import static domain.util.EmployeeRole.WORKER;
import view.coordinator.Coordinator;
import view.form.MainForm;

/**
 *
 * @author Dragon
 */
public class MainFormController {

    private MainForm mainForm;

    public MainForm getMainForm() {
        return mainForm;
    }

    public MainFormController() {
        mainForm = new MainForm(this);
    }

    public void openForm() {//TODO: You could add interface for FormControllers
        prepareForm();
        mainForm.setVisible(true);
    }

    public void closeForm() {
        mainForm.dispose();
    }

    public void openServiceForm() {
        Coordinator.getInstance().openServiceForm();
    }

    public void openShowServicesForm() throws Exception {
        Coordinator.getInstance().openShowServicesForm();
    }

    public void openAddEmployeeForm() {
        Coordinator.getInstance().openAddEmployeeForm();
    }

    public void openShowEmployeesForm() throws Exception {
        Coordinator.getInstance().openShowEmployeesForm();
    }

    private void prepareForm() {
        if (Communication.getInstance().getAuthenticatedEmployee().getEmployeeRole() == WORKER) {
            mainForm.getMenuEmployee().setEnabled(false);
        }
    }

    public void openAddServiceBookForm() {
        Coordinator.getInstance().openAddServiceBookForm();
    }

    public void openShowServiceBooksForm() throws Exception {
        Coordinator.getInstance().openShowServiceBooksForm();
    }
}
