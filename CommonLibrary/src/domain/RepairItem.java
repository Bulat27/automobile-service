/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Dragon
 */
public class RepairItem implements GeneralDObject{
    
    private Repair repair;
    private Integer orderNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private String remark;
    private BigDecimal employeeExpense;
    private BigDecimal additionalExpense;
    private BigDecimal additionalRevenue;
    private Service service;

    public RepairItem() {}

    public RepairItem(Repair repair, Integer orderNumber, LocalDate startDate, LocalDate endDate, String remark, BigDecimal employeeExpense, BigDecimal additionalExpense, BigDecimal additionalRevenue, Service service) {
        this.repair = repair;
        this.orderNumber = orderNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remark = remark;
        this.employeeExpense = employeeExpense;
        this.additionalExpense = additionalExpense;
        this.additionalRevenue = additionalRevenue;
        this.service = service;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getEmployeeExpense() {
        return employeeExpense;
    }

    public void setEmployeeExpense(BigDecimal employeeExpense) {
        this.employeeExpense = employeeExpense;
    }

    public BigDecimal getAdditionalExpense() {
        return additionalExpense;
    }

    public void setAdditionalExpense(BigDecimal additionalExpense) {
        this.additionalExpense = additionalExpense;
    }

    public BigDecimal getAdditionalRevenue() {
        return additionalRevenue;
    }

    public void setAdditionalRevenue(BigDecimal additionalRevenue) {
        this.additionalRevenue = additionalRevenue;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }  

    @Override
    public String getTableName() {
        return "repair_item";
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInsertionColumns() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAtrPlaceHolders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPreparedStatementParameters(PreparedStatement ps) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
