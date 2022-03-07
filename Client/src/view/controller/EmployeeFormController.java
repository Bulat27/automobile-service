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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.Coordinator;
import view.form.EmployeeForm;
import view.util.FormMode;
import static view.util.FormMode.EDIT;

/**
 *
 * @author Dragon
 */
public class EmployeeFormController {

    private EmployeeForm employeeForm;
    private FormMode formMode;
    private Employee selectedEmployee;
    private int selectedRow;

    private static final String DATE_PATTERN = "dd.MM.yyyy";//TODO: Find a better place for this!

    public EmployeeFormController(FormMode formMode, Employee selectedEmployee, int selectedRow) {
        this.formMode = formMode;
        this.selectedEmployee = selectedEmployee;
        this.selectedRow = selectedRow;
        this.employeeForm = new EmployeeForm(Coordinator.getInstance().getMainForm(), true, this);
    }

    public void openForm() {
        prepareForm();
        employeeForm.setVisible(true);
    }

    public void closeForm() {
        employeeForm.dispose();
    }

    public void save(String firstName, String lastName, String role, String hourlyRate, String dateOfEmployment, String username, char[] password) throws Exception {
        validate(firstName, lastName, role, hourlyRate, dateOfEmployment, username, password);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);//TODO: Find a better place for this!

        Employee employee = new Employee(firstName, lastName, EmployeeRole.valueOf(role), new BigDecimal(hourlyRate),
                LocalDate.parse(dateOfEmployment, dtf), username, String.valueOf(password));

        executeSaving(employee);
    }

    private void executeSaving(Employee employee) throws Exception {
        switch (formMode) {
            
            case EDIT:
                edit(employee);
                break;

            case ADD:
                add(employee);
                break;
                
            default:
        }
    }

    public void coordinateForms() {
        closeForm();
    }

    private void prepareForm() {
        prepareRoleComboBox();

        if (formMode == EDIT && selectedEmployee != null) {
            prepareFields();
        }
    }

    private void prepareRoleComboBox() {
        JComboBox roleCMB = employeeForm.getCmbRole();

        roleCMB.removeAllItems();

        for (EmployeeRole role : EmployeeRole.values()) {
            roleCMB.addItem(role);
        }
    }

    private void validate(String firstName, String lastName, String role, String hourlyRate, String dateOfEmployment, String username, char[] password) throws ValidationException {
        Validator.startValidation()
                .validateValueIsAllAlphabets(firstName, "First name field is required and must contain only alphabetic characters!")
                .validateValueIsAllAlphabets(lastName, "Last name field is required and must contain only alphabetic characters!")
                .validateNotNullOrEmpty(role, "Role field is required!")
                .validateValueIsNonNegativeNumber(hourlyRate, "Hourly rate is required and must be a non negative number!")
                .validateValueIsDate(dateOfEmployment, DATE_PATTERN, "Date field is required and date must be in format: " + DATE_PATTERN)
                .validateNotNullOrEmpty(username, "Username field is required!")
                .validateNotNull(password, "Password field is required!")
                .validateNotNullOrEmpty(String.valueOf(password), "Password field is required!")
                .throwIfInvalide();
    }

    private void prepareFields() {
        employeeForm.getTxtFirstName().setText(selectedEmployee.getFirstName());
        employeeForm.getTxtLastName().setText(selectedEmployee.getLastName());
        employeeForm.getCmbRole().setSelectedItem(selectedEmployee.getEmployeeRole());
        employeeForm.getTxtHourlyRate().setText(String.valueOf(selectedEmployee.getHourlyRate()));
        employeeForm.getTxtDateOfEmployment().setText(selectedEmployee.getDateOfEmployment().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));//TODO: Move this somewhere else (the pattern thing)
        employeeForm.getTxtUserName().setText(selectedEmployee.getUsername());
        employeeForm.getTxtPassword().setText(selectedEmployee.getPassword());//TODO: Implement show and hide!  
    }

    private void edit(Employee employee) throws Exception {
        if (selectedEmployee != null) {
            employee.setEmployeeID(selectedEmployee.getEmployeeID());
            EmployeeController.getInstance().editEmployee(employee);
            //TODO: Implement refreshing of the form after edit
            if (selectedRow != -1) {
                Coordinator.getInstance().refreshShowEmployeesForm(employee, selectedRow);
            }
        }
    }

    private void add(Employee employee) throws Exception {
        EmployeeController.getInstance().addEmployee(employee);
    }
}
