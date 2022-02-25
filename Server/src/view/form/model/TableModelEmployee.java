/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form.model;

import domain.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cartman
 */
public class TableModelEmployee extends AbstractTableModel {

    private List<Employee> employees;
    private final String[] columnNames = new String[]{"Username", "First and last name", "Role"};
    private final Class[] columnClass = new Class[]{String.class, String.class, String.class};

    public TableModelEmployee() {
        this.employees = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return employees != null ? employees.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return column > columnNames.length ? "N/A" : columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnIndex > columnClass.length ? Object.class : columnClass[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Employee employee = employees.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return employee.getUsername();
            case 1:
                return employee.toString();
            case 2:
                return employee.getEmployeeRole();
            default:
                return "N/A";
        }
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        //fireTableDataChanged();
        fireTableRowsInserted(employees.size() - 1, employees.size() - 1);//TODO: Make sure this suffices
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        fireTableDataChanged();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
