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
public class Service implements Serializable {

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

    public BigDecimal getCostOfMaterials() {
        return materialCost;
    }

    public void setCostOfMaterials(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    @Override
    public String toString() {
        return name;
    }
}
