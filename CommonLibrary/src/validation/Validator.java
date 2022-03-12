/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import domain.Repair;
import domain.RepairItem;
import domain.validation.RepairItemValidator;
import domain.validation.RepairValidator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dragon
 */
public class Validator {

    private final List<String> validationErrors;

    private Validator() {
        validationErrors = new ArrayList();
    }

    public static Validator startValidation() {
        return new Validator();
    }

    public Validator validateNotNullOrEmpty(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }

    public Validator validateNotNull(Object value, String errorMessage) {
        if (value == null) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }

//    public Validator validateValueIsNonNegativeNumber(String value, String errorMessage) throws ValidationException {
//        try {
//            if (value != null) {
//                BigDecimal number = new BigDecimal(value);
//            } else {
//                this.validationErros.add(errorMessage);
//            }
//        } catch (NumberFormatException nfe) {
//            this.validationErros.add(errorMessage);
//        }
//        return this;
//    }
    public Validator validateValueIsNonNegativeNumber(String value, String errorMessage) {
        if (value == null) {
            this.validationErrors.add(errorMessage);
        } else {
            try {
                BigDecimal number = new BigDecimal(value);
                if (number.compareTo(new BigDecimal(0)) == -1) {
                    this.validationErrors.add(errorMessage);
                }
            } catch (NumberFormatException e) {
                this.validationErrors.add(errorMessage);
            }
        }
        return this;
    }

    public Validator validateValueIsNonNegativeInteger(String value, String errorMessage) {
        if (value == null || value.isEmpty()) {
            this.validationErrors.add(errorMessage);
        } else {
            try {
                int number = Integer.parseInt(value);
                if (number < 0) {
                    this.validationErrors.add(errorMessage);
                }
            } catch (NumberFormatException e) {
                this.validationErrors.add(errorMessage);
            }
        }
        return this;
    }

//    public Validator validateValueIsDate(String value, String pattern, String errorMessage) {//TODO:Do this using LocalDate and other Java 8 stuff
//        //TODO: You could also add a validation in order to check if date is the value in the past or something like that!
//        try {
//            if (value != null) {
//                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//                sdf.parse(value);
//            } else {
//                this.validationErrors.add(errorMessage);
//            }
//        } catch (ParseException ex) {
//            this.validationErrors.add(errorMessage);
//        }
//        return this;
//    }
    public Validator validateValueIsDate(String value, String pattern, String errorMessage) {//TODO:Do this using LocalDate and other Java 8 stuff
        //TODO: You could also add a validation in order to check if date is the value in the past or something like that!
        try {
            if (value != null) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                LocalDate.parse(value, dtf);
            } else {
                this.validationErrors.add(errorMessage);
            }
        } catch (DateTimeParseException ex) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }

    public Validator validateValueIsAllAlphabets(String value, String errorMessage) {
        if (value == null || value.isEmpty() || !value.matches("[a-zA-Z]+")) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }

    public Validator validateListIsNotEmpty(List list, String errorMessage) {
        if (list == null || list.isEmpty()) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }

    public Validator validateNumberIsNonNegative(Number number, String errorMessage) {
        if (number == null || number.doubleValue() < 0) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }

    public Validator validateRepairTotalRevenue(Repair repair, String errorMessage) {
        RepairValidator repairValidator = new RepairValidator();

        try {
            repairValidator.validateTotalRevenue(repair);
        } catch (ValidationException ex) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }

    public Validator validateRepairTotalExpense(Repair repair, String errorMessage) {
        RepairValidator repairValidator = new RepairValidator();

        try {
            repairValidator.validateTotalExpense(repair);
        } catch (ValidationException ex) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }

    public Validator validateRepairItemEmployeeExpense(RepairItem repairItem, String errorMessage) {
        RepairItemValidator repairItemValidator = new RepairItemValidator();

        try {
            repairItemValidator.validateEmployeeExpense(repairItem);
        } catch (ValidationException ex) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
//    public Validator validateIsFirstDateBefore(LocalDate firstDate, LocalDate secondDate, String errorMessage){
//        
//    }

    public Validator throwIfInvalideParameterInstance(Object value, String errorMessage, Class<?> cls) throws ValidationException {
        if (!cls.isInstance(value)) {
//            this.validationErrors.add(errorMessage);
            throw new ValidationException(errorMessage);
        }
        return this;
    }

    public void throwIfInvalide() throws ValidationException {
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(this.validationErrors.stream().collect(Collectors.joining("\n")));
        }
    }
}
