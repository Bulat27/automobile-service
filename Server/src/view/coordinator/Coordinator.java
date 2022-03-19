/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coordinator;

import domain.Employee;
import view.controller.DatabaseConfigurationFormController;
import view.controller.MainFormController;
import view.controller.PortConfigurationFormController;
import view.form.MainForm;
import view.util.RefreshMode;

/**
 *
 * @author Dragon
 */
public class Coordinator {

    private static Coordinator instance;

    private MainFormController mainFormController;
    private PortConfigurationFormController portConfigurationFormController;
    private DatabaseConfigurationFormController databaseConfigurationFormController;

    private Coordinator() {
    }

    public static Coordinator getInstance() {
        if (instance == null) {
            instance = new Coordinator();
        }
        return instance;
    }

    public MainForm getMainForm() {
        return mainFormController.getMainForm();
    }

    public void openMainForm() {
        mainFormController = new MainFormController();
        mainFormController.openForm();
    }

    public void refreshMainForm(Employee employee, RefreshMode refreshMode) {
        mainFormController.refreshForm(employee, refreshMode);
    }

    public void openPortConfigurationForm() {
        portConfigurationFormController = new PortConfigurationFormController();
        portConfigurationFormController.openForm();
    }

    public void openDatabaseConfigurationForm() {
        databaseConfigurationFormController = new DatabaseConfigurationFormController();
        databaseConfigurationFormController.openForm();
    }
}
