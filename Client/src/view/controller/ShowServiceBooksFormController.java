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

    public ShowServiceBooksForm getShowServiceBooksForm() {
        return showServiceBooksForm;
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

    public void delete(int selectedRow) throws Exception {
        TableModelServiceBooks tmsb = (TableModelServiceBooks) showServiceBooksForm.getTblServiceBooks().getModel();
        ServiceBook serviceBook = tmsb.getServiceBook(selectedRow);

        ServiceBookController.getInstance().deleteServiceBook(serviceBook);

        tmsb.removeServiceBook(selectedRow);
    }

    public void openEditServiceBookForm(int selectedRow) {
        TableModelServiceBooks tmsb = (TableModelServiceBooks) showServiceBooksForm.getTblServiceBooks().getModel();

        Coordinator.getInstance().openEditServiceBookForm(tmsb.getServiceBook(selectedRow), selectedRow);
    }

    public void refreshShowServiceBooksForm(ServiceBook serviceBook, int selectedRow) {
        TableModelServiceBooks tmsb = (TableModelServiceBooks) showServiceBooksForm.getTblServiceBooks().getModel();

        tmsb.setServiceBook(serviceBook, selectedRow);
    }

    public void openShowRepairsForm(int selectedRow) throws Exception {
        TableModelServiceBooks tmsb = (TableModelServiceBooks) showServiceBooksForm.getTblServiceBooks().getModel();

        Coordinator.getInstance().openShowRepairsForm(tmsb.getServiceBook(selectedRow), selectedRow);
    }
}
