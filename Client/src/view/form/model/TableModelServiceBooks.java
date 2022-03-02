/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form.model;

import domain.ServiceBook;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cartman
 */
public class TableModelServiceBooks extends AbstractTableModel {

    private List<ServiceBook> serviceBooks;
    private final String[] columnNames = new String[]{"Client first and last name", "Vehicle description", "Initial date", "Active"};
    private final Class[] columnClass = new Class[]{String.class, String.class, String.class, String.class};

    private static final String DATE_PATTERN = "dd.MM.yyyy";//TODO: Find a better place for this!

    public TableModelServiceBooks(List<ServiceBook> serviceBooks) {
        this.serviceBooks = serviceBooks;
    }

    @Override
    public int getRowCount() {
        return serviceBooks != null ? serviceBooks.size() : 0;
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
        ServiceBook serviceBook = serviceBooks.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return serviceBook.toString();
            case 1:
                return serviceBook.getVehicleDescription();
            case 2:
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);
                return serviceBook.getInitialDate().format(dtf);//TODO: Find a better way for this!
            case 3:
                return serviceBook.isActive() ? "YES" : "NO";
            default:
                return "N/A";
        }
    }

    public void addServiceBook(ServiceBook serviceBook) {
        this.serviceBooks.add(serviceBook);
        //fireTableDataChanged();
        fireTableRowsInserted(serviceBooks.size() - 1, serviceBooks.size() - 1);//TODO: Make sure this suffices
    }

    public void removeServiceBook(int index) {
        serviceBooks.remove(index);
        fireTableDataChanged();
    }

    public List<ServiceBook> getServiceBooks() {
        return serviceBooks;
    }

    public void setServiceBooks(List<ServiceBook> serviceBooks) {
        this.serviceBooks = serviceBooks;
        fireTableDataChanged();
    }

    public ServiceBook getServiceBook(int index) {
        return serviceBooks.get(index);
    }

    public void setServiceBook(ServiceBook serviceBook, int selectedRow) {
        serviceBooks.set(selectedRow, serviceBook);
        fireTableDataChanged();
    }
}
