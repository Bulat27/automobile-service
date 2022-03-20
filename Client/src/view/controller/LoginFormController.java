package view.controller;

import communication.Communication;
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

    public Employee logIn(String username, char[] password) throws Exception {
        validate(username, password);

        Employee requestEmployee = new Employee(username, String.valueOf(password));
        Employee employee = EmployeeController.getInstance().login(requestEmployee);
        Communication.getInstance().setAuthenticatedEmployee(employee);

        return employee;
    }

    public void coordinateForms() {
        closeForm();
        Coordinator.getInstance().openMainForm();
    }

    public void closeForm() {
        loginForm.dispose();
    }

    private void validate(String username, char[] password) throws ValidationException {
        Validator.startValidation()
                .validateNotNullOrEmpty(username, "Username field is required!")
                .validateNotNull(password, "Password field is required!")
                .validateNotNullOrEmpty(String.valueOf(password), "Password field is required!")
                .throwIfInvalide();
    }
}
