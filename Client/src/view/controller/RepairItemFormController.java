/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.EmployeeController;
import controller.ServiceController;
import domain.Employee;
import domain.Repair;
import domain.RepairItem;
import domain.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JList;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.Coordinator;
import view.form.RepairItemForm;
import view.util.FormMode;

/**
 *
 * @author Dragon
 */
public class RepairItemFormController {

    private RepairItemForm repairItemForm;
    private FormMode formMode;
    private RepairItem selectedRepairItem;
    private int selectedRow;
    private Repair currentRepair;

    private static final String DATE_PATTERN = "dd.MM.yyyy";//TODO: Find a better place for this!

    public RepairItemFormController(FormMode formMode, RepairItem selectedRepairItem, int selectedRow, Repair currentRepair) {
        this.formMode = formMode;
        this.selectedRepairItem = selectedRepairItem;
        this.selectedRow = selectedRow;
        this.currentRepair = currentRepair;
        this.repairItemForm = new RepairItemForm(Coordinator.getInstance().getRepairForm(), true, this);
    }

    public void openForm() throws Exception {
        prepareForm();
        repairItemForm.setVisible(true);
    }

    private void prepareForm() throws Exception {
        prepareServiceComboBox();
        prepareEmployeeList();
    }

    private void closeForm() {
        repairItemForm.dispose();
    }

    private void prepareServiceComboBox() throws Exception {
        List<Service> services = ServiceController.getInstance().getAllServices();
        JComboBox<Service> cmbService = repairItemForm.getCmbService();

        cmbService.removeAllItems();

        for (Service service : services) {
            cmbService.addItem(service);
        }
    }

    private void prepareEmployeeList() throws Exception {//TODO: Make sure that this works properly!
        List<Employee> employees = EmployeeController.getInstance().getAllEmployees();
        JList<Object> jListEmployees = repairItemForm.getjListEmployees();

        jListEmployees.removeAll();

        jListEmployees.setListData(employees.toArray());
    }

    public void add(String startDate, String endDate, String remark, String additionalExpense, String additionalRevenue, String duration, Service service, List<Employee> employees) throws ValidationException {
        validate(startDate, endDate, remark, additionalExpense, additionalRevenue, duration, service, employees);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);//TODO: Find a better place for this!

        BigDecimal employeeExpense = getEmployeeExpense(new BigDecimal(duration), employees);
        RepairItem repairItem = new RepairItem(currentRepair, currentRepair.getRepairItems().size() + 1,
                LocalDate.parse(startDate, dtf), LocalDate.parse(endDate, dtf), remark, employeeExpense, new BigDecimal(additionalExpense),
                new BigDecimal(additionalRevenue), service);
        //TODO: Add list of EmployeeEngagements to RepairItem

        Coordinator.getInstance().refreshRepairForm(repairItem);
        closeForm();
    }

    private BigDecimal getEmployeeExpense(BigDecimal duration, List<Employee> employees) {//TODO: Make sure that this returns the correct result!
        BigDecimal employeeExpense = BigDecimal.ZERO;

        for (Employee employee : employees) {
            employeeExpense = employeeExpense.add(duration.multiply(employee.getHourlyRate()));
        }
        return employeeExpense;
    }

    //TODO: Add startDate before endDate validation!!!!
    private void validate(String startDate, String endDate, String remark, String additionalExpense, String additionalRevenue, String duration, Service service, List employees) throws ValidationException {
        Validator.startValidation()
                .validateValueIsDate(startDate, DATE_PATTERN, "Start date field is required and date must be in format: " + DATE_PATTERN)
                .validateValueIsDate(endDate, DATE_PATTERN, "End date field is required and date must be in format: " + DATE_PATTERN)
                .validateNotNull(remark, "Remark must not be null!")
                .validateValueIsNonNegativeNumber(additionalExpense, "Additional expense is required and must be a non negative number!")
                .validateValueIsNonNegativeNumber(additionalRevenue, "Additional revenue is required and must be a non negative number!")
                .validateValueIsNonNegativeInteger(duration, "Duration is required and must be a non negative integer!")
                .validateNotNull(service, "Service is required and must not be null!")
                .validateListIsNotEmpty(employees, "At least one employee must be engaged!")
                .throwIfInvalide();
    }
}
