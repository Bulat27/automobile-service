/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import domain.Employee;
import java.io.IOException;
import view.coordinator.ViewCoordinator;
import view.form.MainForm;
import view.form.model.TableModelEmployee;
import view.util.RefreshMode;

/**
 *
 * @author Dragon
 */
public class MainFormController {

    private MainForm mainForm;
    private TableModelEmployee tme;

    public MainFormController() {
        mainForm = new MainForm(this);
    }

    public MainForm getMainForm() {
        return mainForm;
    }
    
    public void openForm() {
        prepareForm();
        mainForm.setVisible(true);
    }

    private void prepareForm() {
        toggleView(true);
        prepareTable();
    }

    private void toggleView(boolean signal) {
        mainForm.getBtnStartServer().setEnabled(signal);
        mainForm.getBtnStopServer().setEnabled(!signal);
        mainForm.getMenuConfiguration().setEnabled(signal);
    }

    private void prepareTable() {
        tme = new TableModelEmployee();
        mainForm.setTableEmployeesModel(tme);
    }

    public void startServer() throws IOException {
        Controller.getInstance().startServer();
        toggleView(false);
    }

    public void stopServer() throws IOException {
        Controller.getInstance().stopServer();
        toggleView(true);
    }

    public void refreshForm(Employee employee, RefreshMode refreshMode) {
        switch (refreshMode) {

            case REFRESH_ADD:
                addEmployee(employee);
                break;

            case REFRESH_DELETE:
                removeEmployee(employee);
                break;

            default:
        }
    }

    public void addEmployee(Employee employee) {
        tme.addEmployee(employee);
    }

    public void removeEmployee(Employee employee) {
        tme.removeEmployee(employee);
    }

    public void openPortConfigurationForm() {
        ViewCoordinator.getInstance().openPortConfigurationForm();
    }

    public void openDatabaseConfigurationForm() {
        ViewCoordinator.getInstance().openDatabaseConfigurationForm();
    }
}
