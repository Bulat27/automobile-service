/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form.model;

import domain.Repair;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dragon
 */
public class TableModeRepairs extends AbstractTableModel {

    private List<Repair> repairs;
    private final String[] columnNames = new String[]{"Name", "Start date", "Total revenue", "Total expense"};
    private final Class[] columnClass = new Class[]{String.class, String.class, BigDecimal.class, BigDecimal.class};

    private static final String DATE_PATTERN = "dd.MM.yyyy";//TODO: Find a better place for this!

    public TableModeRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    @Override
    public int getRowCount() {
        return repairs != null ? repairs.size() : 0;
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
        Repair repair = repairs.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return repair.getName();
            case 1:
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);
                return repair.getStartDate().format(dtf);//TODO: Find a better way for this!
            case 2:
                return repair.getTotalRevenue();
            case 3:
                return repair.getTotalExpense();
            default:
                return "N/A";
        }
    }

    public void addRepair(Repair repair) {
        this.repairs.add(repair);
        //fireTableDataChanged();
        fireTableRowsInserted(repairs.size() - 1, repairs.size() - 1);//TODO: Make sure this suffices
    }

    public void removeRepair(int index) {
        repairs.remove(index);
        fireTableDataChanged();
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
        fireTableDataChanged();
    }

    public Repair getRepair(int index) {
        return repairs.get(index);
    }

    public void setRepair(Repair repair, int selectedRow) {
        repairs.set(selectedRow, repair);
        fireTableDataChanged();
    }
}
