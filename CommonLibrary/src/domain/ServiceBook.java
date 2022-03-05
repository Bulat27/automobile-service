/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Dragon
 */
public class ServiceBook implements GeneralDObject {

    private Long serviceBookID;
    private String clientFirstName;
    private String clientLastName;
    private String vehicleDescription;
    private LocalDate initialDate;
    private boolean active;

    public ServiceBook() {
    }

    public ServiceBook(Long serviceBookID, String clientFirstName, String clientLastName, String vehicleDescription, LocalDate initialDate, boolean active) {
        this.serviceBookID = serviceBookID;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.vehicleDescription = vehicleDescription;
        this.initialDate = initialDate;
        this.active = active;
    }

    public ServiceBook(String clientFirstName, String clientLastName, String vehicleDescription, LocalDate initialDate, boolean active) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.vehicleDescription = vehicleDescription;
        this.initialDate = initialDate;
        this.active = active;
    }

    public Long getServiceBookID() {
        return serviceBookID;
    }

    public void setServiceBookID(Long serviceBookID) {
        this.serviceBookID = serviceBookID;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getTableName() {
        return "service_book";
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new ServiceBook(rs.getLong("id"), rs.getString("client_first_name"), rs.getString("client_last_name"),
                rs.getString("vehicle_description"), rs.getDate("initial_date").toLocalDate(), rs.getInt("active") == 1);
    }

    @Override
    public String getInsertionColumns() {
        return "client_first_name, client_last_name, vehicle_description, initial_date, active";
    }

    @Override
    public String getAtrPlaceHolders() {
        return "?, ?, ?, ?, ?";
    }

    @Override
    public void setPreparedStatementParameters(PreparedStatement ps) throws SQLException {
        ps.setString(1, clientFirstName);
        ps.setString(2, clientLastName);
        ps.setString(3, vehicleDescription);
        ps.setDate(4, Date.valueOf(initialDate));
        ps.setInt(5, active ? 1 : 0);
    }

    @Override
    public String getPKWhereCondition() {
        return "id = " + serviceBookID;
    }

    @Override
    public String getAttributeValuesWhereCondition() {
        String clientFirstNameCondition = clientFirstName.isEmpty() ? "1 = 1" : "client_first_name = '" + clientFirstName + "'";
        String conjuction = " AND ";
        String clientLastNameCondition = clientLastName.isEmpty() ? "1 = 1" : "client_last_name = '" + clientLastName + "'";

        return clientFirstNameCondition += conjuction += clientLastNameCondition;
    }

    @Override
    public String getUpdateColumnsWithPlaceHolders() {
        return "client_first_name = ?, client_last_name = ?, vehicle_description = ?, initial_date = ?, active = ?";
    }

    @Override
    public String toString() {
        return clientFirstName + " " + clientLastName;
    }
    
    public String getGeneralData(){
        return clientFirstName + " " + clientLastName + ", " + vehicleDescription;
    }

    @Override
    public String getFKWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJoinCondition() {
        return "";
    }
}
