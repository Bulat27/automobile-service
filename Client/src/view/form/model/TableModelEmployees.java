package view.form.model;

import constant.MyClientConstants;
import domain.Employee;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dragon
 */
public class TableModelEmployees extends AbstractTableModel {

    private List<Employee> employees;
    private final String[] columnNames = new String[]{"First and last name", "Role", "Hourly rate", "Employment date", "Username"};
    private final Class[] columnClass = new Class[]{String.class, String.class, BigDecimal.class, String.class, String.class};

    public TableModelEmployees(List<Employee> employees) {
        this.employees = employees;
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
                return employee.toString();
            case 1:
                return employee.getEmployeeRole().toString();
            case 2:
                return employee.getHourlyRate();
            case 3:
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(MyClientConstants.DATE_PATTERN);
                return employee.getDateOfEmployment().format(dtf);
            case 4:
                return employee.getUsername();
            default:
                return "N/A";
        }
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        //fireTableDataChanged();
        fireTableRowsInserted(employees.size() - 1, employees.size() - 1);
    }

    public void removeEmployee(int index) {
        employees.remove(index);
        fireTableDataChanged();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        fireTableDataChanged();
    }

    public Employee getEmployee(int index) {
        return employees.get(index);
    }

    public void setEmployee(Employee employee, int selectedRow) {
        employees.set(selectedRow, employee);
        fireTableDataChanged();
    }
}
