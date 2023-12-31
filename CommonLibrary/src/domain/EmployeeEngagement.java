package domain;

import domain.util.EmployeeRole;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dragon
 */
public class EmployeeEngagement implements GeneralDObject {

    private Employee employee;
    private RepairItem repairItem;
    private int duration;

    public EmployeeEngagement() {
    }

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

    @Override
    public String getTableName() {
        return "employee_engagement";
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        Employee e = new Employee(rs.getLong("e.id"), rs.getString("e.first_name"), rs.getString("e.last_name"),
                EmployeeRole.valueOf(rs.getString("e.role")), rs.getBigDecimal("e.hourly_rate"),
                rs.getDate("e.date_of_employment").toLocalDate(), rs.getString("e.username"), rs.getString("e.password"));

        return new EmployeeEngagement(e, repairItem, rs.getInt("eeng.duration"));
    }

    @Override
    public String getInsertionColumns() {
        return "employee_id, repair_id, order_number, duration";
    }

    @Override
    public String getAtrPlaceHolders() {
        return "?, ?, ?, ?";
    }

    @Override
    public void setPreparedStatementParameters(PreparedStatement ps) throws SQLException {
        ps.setLong(1, employee.getEmployeeID());
        ps.setLong(2, repairItem.getRepair().getRepairID());
        ps.setInt(3, repairItem.getOrderNumber());
        ps.setInt(4, duration);
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
        return "eeng.repair_id = " + repairItem.getRepair().getRepairID() + " AND eeng.order_number = " + repairItem.getOrderNumber();
    }

    @Override
    public String getJoinCondition() {
        return " eeng JOIN employee e ON eeng.employee_id = e.id";
    }

    @Override
    public void setAutoGeneratedKey(long id) {

    }
}
