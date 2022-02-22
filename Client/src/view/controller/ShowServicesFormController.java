/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.ServiceController;
import domain.Service;
import java.util.List;
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
    
    public void openForm() throws Exception{
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
}
