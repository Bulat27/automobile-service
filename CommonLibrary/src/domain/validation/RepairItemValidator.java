/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.validation;

import domain.EmployeeEngagement;
import domain.RepairItem;
import java.math.BigDecimal;
import validation.ValidationException;

/**
 *
 * @author Dragon
 */
public class RepairItemValidator {

    public void validateEmployeeExpense(RepairItem repairItem) throws ValidationException {
        if (repairItem == null || repairItem.getEmployeeEngagements() == null) {
            throw new ValidationException();
        } else {
            BigDecimal employeeExpense = BigDecimal.ZERO;

            for (EmployeeEngagement employeeEngagement : repairItem.getEmployeeEngagements()) {
                employeeExpense = employeeExpense.add(employeeEngagement.getEmployee().getHourlyRate()
                        .multiply(BigDecimal.valueOf(employeeEngagement.getDuration())));
            }

            if (repairItem.getEmployeeExpense().compareTo(employeeExpense) != 0) {
                throw new ValidationException();
            }
        }
    }
}
