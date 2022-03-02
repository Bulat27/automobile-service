/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.ServiceBookController;
import domain.ServiceBook;
import java.time.LocalDate;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.Coordinator;
import view.form.ServiceBookForm;
import view.util.FormMode;

/**
 *
 * @author Dragon
 */
public class ServiceBookFormController {
    
    private ServiceBookForm serviceBookForm;
    private FormMode formMode;
    
    public ServiceBookFormController(FormMode formMode) {
        this.formMode = formMode;
        serviceBookForm = new ServiceBookForm(Coordinator.getInstance().getMainForm(), true, this);
    }
    
    public void openForm() {
        prepareForm();
        serviceBookForm.setVisible(true);
    }
    
    public void closeForm() {
        serviceBookForm.dispose();
    }
    
    private void prepareForm() {
        
    }
    
    public void save(String clientFirstName, String clientLastName, String vehicleDescription, boolean active) throws Exception {
        validate(clientFirstName, clientLastName, vehicleDescription);
        
        ServiceBook serviceBook = new ServiceBook(clientFirstName, clientLastName, vehicleDescription, LocalDate.now(), active);
        
        executeSaving(serviceBook);
    }
    
    public void coordinateForms() {
        closeForm();
    }
    
    private void validate(String clientFirstName, String clientLastName, String vehicleDescription) throws ValidationException {
        Validator.startValidation()
                .validateValueIsAllAlphabets(clientFirstName, "Client first name field is required and must contain only alphabetic characters!")
                .validateValueIsAllAlphabets(clientLastName, "Client last name field is required and must contain only alphabetic characters!")
                .validateNotNullOrEmpty(vehicleDescription, "Vehicle description field is required!")
                .throwIfInvalide();
    }
    
    private void executeSaving(ServiceBook serviceBook) throws Exception {
        switch (formMode) {
            
            case ADD:
                add(serviceBook);
                break;
            
            case EDIT:
                
                break;
            
            default:
        }
    }
    
    private void add(ServiceBook serviceBook) throws Exception {
        ServiceBookController.getInstance().addServiceBook(serviceBook);
    }
}
