/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Dragon
 */
public class EmployeeEngagement implements Serializable{
    
    private Employee employee;
    private RepairItem repairItem;
    private int duration;

    public EmployeeEngagement() {}

    public EmployeeEngagement(Employee employee, RepairItem repairItem, int duration) {
        this.employee = employee;
        this.repairItem = repairItem;
        this.duration = duration;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public RepairItem getRepairItem() {
        return repairItem;
    }

    public void setRepairItem(RepairItem repairItem) {
        this.repairItem = repairItem;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
