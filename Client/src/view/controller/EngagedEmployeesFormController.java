/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import domain.Employee;
import java.util.List;
import view.coordinator.Coordinator;
import view.form.EngagedEmployeesForm;
import view.form.model.TableModelEngagedEmployees;

/**
 *
 * @author Dragon
 */
public class EngagedEmployeesFormController {

    private EngagedEmployeesForm engagedEmployeesForm;
    private List<Employee> employees;

    public EngagedEmployeesFormController(List<Employee> employees) {
        this.employees = employees;
        engagedEmployeesForm = new EngagedEmployeesForm(Coordinator.getInstance().getRepairForm(), true, this);
    }

    public void openForm() {
        prepareTable();
        engagedEmployeesForm.setVisible(true);
    }

    private void prepareTable() {
        TableModelEngagedEmployees tmee = new TableModelEngagedEmployees(employees);

        engagedEmployeesForm.setTableEngagedEmployeesModel(tmee);
    }
}
