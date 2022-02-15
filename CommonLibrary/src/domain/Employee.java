/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Dragon
 */
public class Employee implements GeneralDObject{
    
    private Long employeeID;
    private String firstName;
    private String lastName;
    private boolean adminStatus;
    private BigDecimal hourlyRate;
    private LocalDate dateOfEmployment;
    private String username;
    private String password;

    public Employee() {}

    public Employee(Long employeeID, String firstName, String lastName, boolean adminStatus, BigDecimal hourlyRate, LocalDate dateOfEmployment, String username, String password) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adminStatus = adminStatus;
        this.hourlyRate = hourlyRate;
        this.dateOfEmployment = dateOfEmployment;
        this.username = username;
        this.password = password;
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public String getTableName() {
        return "employee";
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("admin_status") == 1, rs.getBigDecimal("hourly_rate"), rs.getDate("date_of_employment").toLocalDate(), rs.getString("username"), rs.getString("password"));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.employeeID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {//TODO: You can also use username here once it is unique!
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        return true;
    }
}
