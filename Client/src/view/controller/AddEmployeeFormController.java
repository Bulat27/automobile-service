/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.EmployeeController;
import domain.Employee;
import domain.util.EmployeeRole;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.Coordinator;
import view.form.AddEmployeeForm;

/**
 *
 * @author Dragon
 */
public class AddEmployeeFormController {

    private AddEmployeeForm addEmployeeForm;
    private static final String DATE_PATTERN = "dd.MM.yyyy";//TODO: Find a better place for this!

    public AddEmployeeFormController() {
        this.addEmployeeForm = new AddEmployeeForm(Coordinator.getInstance().getMainForm(), true, this);
    }

    public void openForm() {
        prepareForm();
        addEmployeeForm.setVisible(true);
    }

    public void closeForm() {
        addEmployeeForm.dispose();
    }

    public void save(String firstName, String lastName, String role, String hourlyRate, String dateOfEmployment, String username, char[] password) throws Exception {
        validate(firstName, lastName, role, hourlyRate, dateOfEmployment, username, password);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);
        
        Employee employee = new Employee(firstName, lastName, EmployeeRole.valueOf(role), new BigDecimal(hourlyRate),
                LocalDate.parse(dateOfEmployment, dtf), username, String.valueOf(password));

        EmployeeController.getInstance().saveEmployee(employee);
    }

    public void coordinateForms() {
        closeForm();
    }

    private void prepareForm() {
        prepareRoleComboBox();
    }

    private void prepareRoleComboBox() {
        JComboBox roleCMB = addEmployeeForm.getCmbRole();

        roleCMB.removeAllItems();

        for (EmployeeRole role : EmployeeRole.values()) {
            roleCMB.addItem(role);
        }
    }

    private void validate(String firstName, String lastName, String role, String hourlyRate, String dateOfEmployment, String username, char[] password) throws ValidationException {
        Validator.startValidation()
                .validateNotNullOrEmpty(firstName, "First name field is required!")
                .validateNotNullOrEmpty(lastName, "Last name field is required!")
                .validateNotNullOrEmpty(role, "Role field is required!")
                .validateValueIsNonNegativeNumber(hourlyRate, "Hourly rate is required and must be a non negative number!")
                .validateValueIsDate(dateOfEmployment, DATE_PATTERN, "Date field is required and date must be in format: " + DATE_PATTERN)
                .validateNotNullOrEmpty(username, "Username field is required!")
                .validateNotNull(password, "Password field is required!")
                .validateNotNullOrEmpty(String.valueOf(password), "Password field is required!")
                .throwIfInvalide();
    }

}
