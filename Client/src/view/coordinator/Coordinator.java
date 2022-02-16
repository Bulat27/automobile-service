/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coordinator;

import view.controller.LoginFormController;
import view.controller.MainFormController;

/**
 *
 * @author Dragon
 */
public class Coordinator {

    private static Coordinator instance;

    private LoginFormController loginController;
    private MainFormController mainFormController;

    private Coordinator() {}

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

    public void closeLoginForm() {
        loginController.closeForm();
    }

    public void openMainForm() {
        mainFormController = new MainFormController();
        mainFormController.openForm();
    }
}
