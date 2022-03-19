/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import properties.util.UtilApplicationProperties;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.Coordinator;
import view.form.DatabaseConfigurationForm;

/**
 *
 * @author Dragon
 */
public class DatabaseConfigurationFormController {

    private DatabaseConfigurationForm databaseConfigurationForm;

    public DatabaseConfigurationFormController() {
        databaseConfigurationForm = new DatabaseConfigurationForm(Coordinator.getInstance().getMainForm(), true, this);
    }

    public void openForm() {
        databaseConfigurationForm.setVisible(true);
    }

    public void closeForm() {
        databaseConfigurationForm.setVisible(false);
    }

    public void save(String url, String username, String password) throws Exception {
        validate(url, username, password);

        UtilApplicationProperties.getInstance().setDatabaseURL(url);
        UtilApplicationProperties.getInstance().setDatabaseUsername(username);
        UtilApplicationProperties.getInstance().setDatabasePassword(password);
        UtilApplicationProperties.getInstance().saveChanges();
    }

    public void coordinateForms() {
        closeForm();
    }

    private void validate(String url, String username, String password) throws ValidationException {
        Validator.startValidation()
                .validateNotNullOrEmpty(url, "URL field is required")
                .validateNotNullOrEmpty(username, "Username field is required")
                .validateNotNullOrEmpty(password, "Password field is required")
                .throwIfInvalide();
    }
}
