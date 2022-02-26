/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.EmployeeController;
import domain.Employee;
import java.util.List;
import view.coordinator.Coordinator;
import view.form.ShowEmployeesForm;
import view.form.model.TableModelEmployee;

/**
 *
 * @author Dragon
 */
public class ShowEmployeesFormController {

    private ShowEmployeesForm showEmployeesForm;

    public ShowEmployeesFormController() {
        showEmployeesForm = new ShowEmployeesForm(Coordinator.getInstance().getMainForm(), true, this);
    }

    public void openForm() throws Exception {
        prepareForm();
        showEmployeesForm.setVisible(true);
    }

    private void prepareForm() throws Exception {
        prepareTable();
    }

    private void prepareTable() throws Exception {
        List<Employee> employees = EmployeeController.getInstance().getAllEmployees();
        TableModelEmployee tme = new TableModelEmployee(employees);

        showEmployeesForm.setTableEmployeesModel(tme);
    }
}
