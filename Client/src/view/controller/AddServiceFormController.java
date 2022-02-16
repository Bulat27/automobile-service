/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.ServiceController;
import domain.Service;
import java.math.BigDecimal;
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

    public void save(BigDecimal price, String name, String description, BigDecimal materialCost) throws Exception {
        //TODO:Dodaj validaciju!
        Service service = new Service(price, name, description, materialCost);
        
        ServiceController.getInstance().saveService(service);
    }

    public void coordinateForms() {
        closeForm();
    }
}
