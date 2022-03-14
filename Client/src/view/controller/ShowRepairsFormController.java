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
import view.util.RefreshMode;

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

    public ShowRepairsForm getShowRepairsForm() {
        return showRepairsForm;
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
        Repair repair = getRepairWithCurrentServiceBook();

        List<Repair> repairs = RepairController.getInstance().getRepairsByFKCondition(repair);
        TableModeRepairs tmr = new TableModeRepairs(repairs);

        selectedServiceBook.setRepairs(repairs);
        showRepairsForm.setTableRepairsModel(tmr);
    }

    private void prepareGeneralData() {
        showRepairsForm.getLblServiceBookGeneralData().setText(selectedServiceBook.getGeneralData());
    }

    public void openAddRepairForm() throws Exception {
        Coordinator.getInstance().openAddRepairForm(getRepairWithCurrentServiceBook());
    }

    private Repair getRepairWithCurrentServiceBook() {
        Repair repair = new Repair();

        repair.setServiceBook(selectedServiceBook);

        return repair;
    }

    public void openEditRepairForm(int selectedRow) throws Exception {
        TableModeRepairs tmr = (TableModeRepairs) showRepairsForm.getTblRepairs().getModel();

        Coordinator.getInstance().openEditRepairForm(tmr.getRepair(selectedRow), selectedRow);
    }

    public void refreshForm(Repair repair, RefreshMode refreshMode) {
        switch (refreshMode) {

            case REFRESH_ADD:
                TableModeRepairs tmr = (TableModeRepairs) showRepairsForm.getTblRepairs().getModel();
                tmr.addRepair(repair);
                break;
        }
    }

    public void delete(int selectedRow) throws Exception{
        TableModeRepairs tmr = (TableModeRepairs) showRepairsForm.getTblRepairs().getModel();
        Repair repair = tmr.getRepair(selectedRow);
        
        RepairController.getInstance().deleteRepair(repair);
        
        tmr.removeRepair(selectedRow);
    }
}
