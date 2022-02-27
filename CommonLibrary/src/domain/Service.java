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

/**
 *
 * @author Dragon
 */
public class Service implements GeneralDObject {

    private Long serviceID;
    private BigDecimal price;
    private String name;
    private String description;
    private BigDecimal materialCost;

    public Service() {}

    public Service(Long serviceID, BigDecimal price, String name, String description, BigDecimal materialCost) {
        this.serviceID = serviceID;
        this.price = price;
        this.name = name;
        this.description = description;
        this.materialCost = materialCost;
    }

    public Service(BigDecimal price, String name, String description, BigDecimal materialCost) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.materialCost = materialCost;
    }
    
    public Long getServiceID() {
        return serviceID;
    }

    public void setServiceID(Long serviceID) {
        this.serviceID = serviceID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getTableName() {
        return "service";
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Service(rs.getLong("id"), rs.getBigDecimal("price"), rs.getString("name"), rs.getString("description"), rs.getBigDecimal("material_cost"));
    }

    @Override
    public String getInsertionColumns() {
        return "price, name, description, material_cost";
    }

    @Override
    public String getAtrPlaceHolders() {
        return "? , ? , ?, ?";
    }

    @Override
    public void setPreparedStatementParameters(PreparedStatement ps) throws SQLException{
        ps.setBigDecimal(1, price);
        ps.setString(2, name);
        ps.setString(3, description);
        ps.setBigDecimal(4, materialCost);
    }

    @Override
    public String getPKWhereCondition() {
        return "id = " + serviceID;
    }

    @Override
    public String getWhereCondition() {
        return "name like '%" + name + "%'";
    }

    @Override
    public String getUpdateColumnsWithPlaceHolders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
