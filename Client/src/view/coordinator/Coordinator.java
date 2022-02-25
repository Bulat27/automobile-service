/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coordinator;

import view.controller.AddEmployeeFormController;
import view.controller.AddServiceFormController;
import view.controller.LoginFormController;
import view.controller.MainFormController;
import view.controller.ShowServicesFormController;
import view.form.MainForm;

/**
 *
 * @author Dragon
 */
public class Coordinator {

    private static Coordinator instance;

    private LoginFormController loginController;
    private MainFormController mainFormController;
    private AddServiceFormController addServiceFormController;
    private ShowServicesFormController showServicesFormController;
    private AddEmployeeFormController addEmployeeFormController;

    private Coordinator() {
    }

    public static Coordinator getInstance() {
        if (instance == null) {
            instance = new Coordinator();
        }
        return instance;
    }

    public void openLoginForm() {
        loginController = new LoginFormController();
        loginController.openForm();
    }

//    public void closeLoginForm() {
//        loginController.closeForm();
//    }
    public void openMainForm() {
        mainFormController = new MainFormController();
        mainFormController.openForm();
    }

    public void openAddServiceForm() {
        addServiceFormController = new AddServiceFormController();
        addServiceFormController.openForm();
    }

    public MainForm getMainForm() {
        return mainFormController.getMainForm();
    }

    public void openShowServicesForm() throws Exception {
        showServicesFormController = new ShowServicesFormController();
        showServicesFormController.openForm();
    }

    public void openAddEmployeeForm() {
        addEmployeeFormController = new AddEmployeeFormController();
        addEmployeeFormController.openForm();
    }
}
