/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.ServiceBookController;
import domain.ServiceBook;
import java.util.List;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.Coordinator;
import view.form.ShowServiceBooksForm;
import view.form.model.TableModelServiceBooks;

/**
 *
 * @author Dragon
 */
public class ShowServiceBooksFormController {

    private ShowServiceBooksForm showServiceBooksForm;

    public ShowServiceBooksFormController() {
        showServiceBooksForm = new ShowServiceBooksForm(Coordinator.getInstance().getMainForm(), true, this);
    }

    public void openForm() throws Exception {
        prepareForm();
        showServiceBooksForm.setVisible(true);
    }

    private void prepareForm() throws Exception {
        prepareTable();
    }

    private void prepareTable() throws Exception {
        List<ServiceBook> serviceBooks = ServiceBookController.getInstance().getAllServiceBooks();
        TableModelServiceBooks tmsb = new TableModelServiceBooks(serviceBooks);

        showServiceBooksForm.setTableServiceBooksModel(tmsb);
    }

    public void search(String clientFirstName, String clientLastName) throws Exception {
        validate(clientFirstName, clientLastName);

        ServiceBook serviceBook = getServiceBookWithCondition(clientFirstName, clientLastName);

        List<ServiceBook> serviceBooks = ServiceBookController.getInstance().getServiceBooksByCondition(serviceBook);

        TableModelServiceBooks tmsb = (TableModelServiceBooks) showServiceBooksForm.getTblServiceBooks().getModel();
        tmsb.setServiceBooks(serviceBooks);
    }

    private void validate(String clientFirstName, String clientLastName) throws ValidationException {
        Validator.startValidation()
                .validateNotNull(clientFirstName, "Client first name must not be null!")
                .validateNotNull(clientLastName, "Client last name must not be null!")
                .throwIfInvalide();
    }

    private ServiceBook getServiceBookWithCondition(String clientFirstName, String clientLastName) {
        ServiceBook serviceBook = new ServiceBook();

        serviceBook.setClientFirstName(clientFirstName);
        serviceBook.setClientLastName(clientLastName);

        return serviceBook;
    }
}
