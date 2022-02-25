/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.ServiceController;
import domain.Service;
import java.math.BigDecimal;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.Coordinator;
import view.form.AddServiceForm;

/**
 *
 * @author Dragon
 */
public class AddServiceFormController {

    private AddServiceForm addServiceForm;

    public AddServiceFormController() {
        addServiceForm = new AddServiceForm(Coordinator.getInstance().getMainForm(), true, this);
    }

    public void openForm() {
        addServiceForm.setVisible(true);
    }

    public void closeForm() {
        addServiceForm.dispose();
    }

    public void save(String price, String name, String description, String materialCost) throws Exception {
        validate(price, name, materialCost);
        Service service = new Service(new BigDecimal(price), name, description, new BigDecimal(materialCost));
        
        ServiceController.getInstance().saveService(service);
    }

    public void coordinateForms() {
        closeForm();
    }

    private void validate(String price, String name, String materialCost) throws ValidationException {
        Validator.startValidation()
                 .validateNotNullOrEmpty(name, "Name field is required!")
                 .validateValueIsNonNegativeNumber(price, "Price is required and must be a non negative number!")
                 .validateValueIsNonNegativeNumber(materialCost, "Material cost is required and must be a non negative number!")
                 .throwIfInvalide();
    }

    
}
