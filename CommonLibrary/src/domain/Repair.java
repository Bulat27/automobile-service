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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class Repair implements GeneralDObject {

    private Long repairID;
    private String name;
    private LocalDate startDate;
    private BigDecimal totalRevenue = BigDecimal.ZERO;
    private BigDecimal totalExpense = BigDecimal.ZERO;
    private ServiceBook serviceBook;
    private List<RepairItem> repairItems = new ArrayList<>();

    public Repair() {
    }

    public Repair(Long repairID, BigDecimal totalRevenue, BigDecimal totalExpense, String name, LocalDate startDate, ServiceBook serviceBook) {
        this.repairID = repairID;
        this.totalRevenue = totalRevenue;
        this.totalExpense = totalExpense;
        this.name = name;
        this.startDate = startDate;
        this.serviceBook = serviceBook;
//        this.repairItems = new ArrayList<>();
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

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ServiceBook getServiceBook() {
        return serviceBook;
    }

    public void setServiceBook(ServiceBook serviceBook) {
        this.serviceBook = serviceBook;
    }

    public List<RepairItem> getRepairItems() {
        return repairItems;
    }

    public void setRepairItems(List<RepairItem> repairItems) {
        this.repairItems = repairItems;
    }

    @Override
    public String getTableName() {
        return "repair";
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
//        ServiceBook sb = new ServiceBook(rs.getLong("sb.id"), rs.getString("sb.client_first_name"), rs.getString("sb.client_last_name"),
//                rs.getString("sb.vehicle_description"), rs.getDate("sb.initial_date").toLocalDate(), rs.getInt("sb.active") == 1);

//        return new Repair(rs.getLong("r.id"), rs.getBigDecimal("r.total_revenue"), rs.getBigDecimal("r.total_expense"),
//                rs.getString("r.name"), rs.getDate("r.start_date").toLocalDate(), serviceBook);
        return new Repair(rs.getLong("id"), rs.getBigDecimal("total_revenue"), rs.getBigDecimal("total_expense"),
                rs.getString("name"), rs.getDate("start_date").toLocalDate(), serviceBook);
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

    @Override
    public String getPKWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAttributeValuesWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateColumnsWithPlaceHolders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFKWhereCondition() {
//        return "r.service_book_id = " + serviceBook.getServiceBookID();
        return "service_book_id = " + serviceBook.getServiceBookID();
    }

    @Override
    public String getJoinCondition() {
//        return " r JOIN service_book sb ON r.service_book_id = sb.id";
        return "";
    }
}
