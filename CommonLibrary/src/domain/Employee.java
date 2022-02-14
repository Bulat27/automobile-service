/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Dragon
 */
public class Employee implements Serializable{
    
    private Long employeeID;
    private String firstName;
    private String lastName;
    private boolean adminStatus;
    private BigDecimal hourlyRate;
    private LocalDate dateOfEmployment;

    public Employee() {}

    public Employee(Long employeeID, String firstName, String lastName, boolean adminStatus, BigDecimal hourlyRate, LocalDate dateOfEmployment) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adminStatus = adminStatus;
        this.hourlyRate = hourlyRate;
        this.dateOfEmployment = dateOfEmployment;
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

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
