/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.ServiceController;
import domain.Service;
import java.util.List;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.Coordinator;
import view.form.ShowServicesForm;
import view.form.model.TableModelServices;

/**
 *
 * @author Dragon
 */
public class ShowServicesFormController {

    private ShowServicesForm showServicesForm;

    public ShowServicesFormController() {
        showServicesForm = new ShowServicesForm(Coordinator.getInstance().getMainForm(), true, this);
    }

    public void openForm() throws Exception {
        prepareForm();
        showServicesForm.setVisible(true);
    }

    private void prepareForm() throws Exception {
        prepareTable();
    }

    private void prepareTable() throws Exception {
        List<Service> services = ServiceController.getInstance().getAllServices();
        TableModelServices tms = new TableModelServices(services);

        showServicesForm.setTableServicesModel(tms);
    }

    public void delete(int selectedRow) throws Exception {
        TableModelServices tms = (TableModelServices) showServicesForm.getTblServices().getModel();
        Service service = tms.getService(selectedRow);

        ServiceController.getInstance().deleteService(service);

        tms.removeService(selectedRow);
    }

    public void search(String name) throws Exception {
        validate(name);

        Service s = getServiceWithCondition(name);

        List<Service> services = ServiceController.getInstance().getServicesByCondition(s);

        TableModelServices tms = (TableModelServices) showServicesForm.getTblServices().getModel();
        tms.setServices(services);//TODO: Make sure that this suffices, and that there is no need to instantiate new TableModelServices
    }

    private Service getServiceWithCondition(String name) {
        Service s = new Service();
        s.setName(name);
        return s;
    }

    private void validate(String name) throws ValidationException {
        Validator.startValidation()
                .validateNotNull(name, "Name must not be null!")
                .throwIfInvalide();
    }
}
