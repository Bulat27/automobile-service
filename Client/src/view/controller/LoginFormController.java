/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.EmployeeController;
import domain.Employee;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.Coordinator;
import view.form.LoginForm;

/**
 *
 * @author Dragon
 */
public class LoginFormController {

    private LoginForm loginForm;

    public LoginFormController() {
        loginForm = new LoginForm(this);
    }

    public void openForm() {
        loginForm.setVisible(true);
    }

//    public void logIn(String username, char[] password) {
//          try {//TODO: Very important! Each form should have a FormController. Also, you need a Coordinator to coordinate all the forms and their Controllers!
//            loginForm.validateForm();//TODO: This needs to be changed! Separate Validator!
//
//            Employee requestEmployee = new Employee(username, String.valueOf(password));
//            
//            Employee employee = EmployeeController.getInstance().login(requestEmployee);
//            JOptionPane.showMessageDialog(loginForm, "Welcome, " + employee.getFirstName());
//            loginForm.dispose();
////            EmployeeController.getInstance().setCurrentUser(employee);//TODO: Consider saving it on the client and server side or just the server side! 
//            new MainForm().setVisible(true);
//        } catch (Exception ex) {
//            ex.printStackTrace();//TODO: Delete this!
//            JOptionPane.showMessageDialog(loginForm, ex.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    
        public Employee logIn(String username, char[] password) throws Exception {
          //TODO: Very important! Each form should have a FormController. Also, you need a Coordinator to coordinate all the forms and their Controllers!
            validate(username, password);//TODO: This needs to be changed! Separate Validator!

            Employee requestEmployee = new Employee(username, String.valueOf(password));
            
            Employee employee = EmployeeController.getInstance().login(requestEmployee);
//            loginForm.dispose();
//            EmployeeController.getInstance().setCurrentUser(employee);//TODO: Consider saving it on the client and server side or just the server side! 
//            new MainForm().setVisible(true);
            
            return employee;
    }

    public void coordinateForms() {
        closeForm();
        Coordinator.getInstance().openMainForm();
    }

    public void closeForm() {
        loginForm.dispose();
    }
    
    private void validate(String username, char[] password) throws ValidationException{
        Validator.startValidation()
                .validateNotNullOrEmpty(username, "Username field is required!")
                .validateNotNull(password, "Password field is required!")
                .validateNotNullOrEmpty(String.valueOf(password), "Password field is required!")
                .throwIfInvalide();
    }
}
