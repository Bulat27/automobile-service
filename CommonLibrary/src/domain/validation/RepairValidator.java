/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.validation;

import domain.Repair;
import domain.RepairItem;
import java.math.BigDecimal;
import validation.ValidationException;

/**
 *
 * @author Dragon
 */
public class RepairValidator {

    public void validateTotalRevenue(Repair repair) throws ValidationException {
        if (repair == null || repair.getRepairItems() == null) {
            throw new ValidationException();
        } else {
            BigDecimal totalRevenue = BigDecimal.ZERO;

            for (RepairItem repairItem : repair.getRepairItems()) {
                totalRevenue = totalRevenue.add(repairItem.getService().getPrice().add(repairItem.getAdditionalRevenue()));
            }

            if (repair.getTotalRevenue().compareTo(totalRevenue) != 0) {
                throw new ValidationException();
            }
        }
    }

    public void validateTotalExpense(Repair repair) throws ValidationException {
        if (repair == null || repair.getRepairItems() == null) {
            throw new ValidationException();
        } else {
            BigDecimal totalExpense = BigDecimal.ZERO;

            for (RepairItem repairItem : repair.getRepairItems()) {
                totalExpense = totalExpense.add(repairItem.getService().getMaterialCost().add(repairItem.getEmployeeExpense().add(repairItem.getAdditionalExpense())));
            }

            if (repair.getTotalExpense().compareTo(totalExpense) != 0) {
                throw new ValidationException();
            }
        }
    }
}
