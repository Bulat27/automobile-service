/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.RepairController;
import domain.Repair;
import domain.ServiceBook;
import java.util.List;
import view.coordinator.Coordinator;
import view.form.ShowRepairsForm;
import view.form.model.TableModeRepairs;

/**
 *
 * @author Dragon
 */
public class ShowRepairsFormController {

    private ShowRepairsForm showRepairsForm;
    private ServiceBook selectedServiceBook;
    private int selectedRow;

    public ShowRepairsFormController(ServiceBook serviceBook, int selectedRow) {
        this.selectedServiceBook = serviceBook;
        this.selectedRow = selectedRow;
        showRepairsForm = new ShowRepairsForm(Coordinator.getInstance().getShowServiceBooksForm(), true, this);
    }

    public void openForm() throws Exception {
        prepareForm();
        showRepairsForm.setVisible(true);
    }

    private void prepareForm() throws Exception {
        prepareGeneralData();
        prepareTable();
    }

    private void prepareTable() throws Exception {
        Repair repair = getRepairWithFKCondition();

        List<Repair> repairs = RepairController.getInstance().getRepairsByFKCondition(repair);
        TableModeRepairs tmr = new TableModeRepairs(repairs);

        showRepairsForm.setTableRepairsModel(tmr);
    }

    private void prepareGeneralData() {
        showRepairsForm.getLblServiceBookGeneralData().setText(selectedServiceBook.getGeneralData());
    }

    private Repair getRepairWithFKCondition() {
        Repair repair = new Repair();

        repair.setServiceBook(selectedServiceBook);

        return repair;
    }
}
