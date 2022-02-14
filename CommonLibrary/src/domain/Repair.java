/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Dragon
 */
public class Repair implements Serializable {

    private Long repairID;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpense;
    private ServiceBook serviceBook;

    public Repair() {}

    public Repair(Long repairID, BigDecimal totalRevenue, BigDecimal totalExpense, ServiceBook serviceBook) {
        this.repairID = repairID;
        this.totalRevenue = totalRevenue;
        this.totalExpense = totalExpense;
        this.serviceBook = serviceBook;
    }

    public Long getRepairID() {
        return repairID;
    }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpense;
    }

    public void setTotalExpenses(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }

    public ServiceBook getServiceBook() {
        return serviceBook;
    }

    public void setServiceBook(ServiceBook serviceBook) {
        this.serviceBook = serviceBook;
    }
}
