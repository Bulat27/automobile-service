/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.RepairItemController;
import domain.Repair;
import domain.RepairItem;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import view.coordinator.Coordinator;
import view.form.RepairForm;
import view.form.model.TableModelRepairItems;
import view.util.FormMode;
import static view.util.FormMode.ADD;
import static view.util.FormMode.EDIT;

/**
 *
 * @author Dragon
 */
public class RepairFormController {

    private RepairForm repairForm;
    private FormMode formMode;
    private Repair currentRepair;
    private int selectedRow;

    private static final String DATE_PATTERN = "dd.MM.yyyy";//TODO: Find a better place for this!

    public RepairFormController(FormMode formMode, Repair selectedRepair, int selectedRow) {
        this.formMode = formMode;
        this.currentRepair = selectedRepair;
        this.selectedRow = selectedRow;
        this.repairForm = new RepairForm(Coordinator.getInstance().getShowRepairsForm(), true, this);
    }

    public RepairForm getRepairForm() {
        return repairForm;
    }

    public void openForm() throws Exception {
        prepareForm();
        repairForm.setVisible(true);
    }

    private void prepareForm() throws Exception {
        prepareTable();

//        if (formMode == ADD && currentRepair == null) {
//            currentRepair = new Repair();
//        }
        if (formMode == EDIT && currentRepair != null) {
            prepareFields();
        }
        System.out.println(currentRepair.getServiceBook().toString() + " " + currentRepair.getServiceBook().getServiceBookID());
    }

    private void prepareTable() throws Exception {
        List<RepairItem> repairItems = getRepairItemList();
        TableModelRepairItems tmri = new TableModelRepairItems(repairItems);

        repairForm.setTableRepairItemsModel(tmri);
    }

    private List<RepairItem> getRepairItemList() throws Exception {
        switch (formMode) {

            case ADD:
                return new ArrayList<>();

            case EDIT:
                RepairItem repairItem = getRepairItemWithFKCondition();
                List<RepairItem> repairItems = RepairItemController.getInstance().getRepairItemsByFKCondition(repairItem);

                currentRepair.setRepairItems(repairItems);

                return repairItems;

            default:
                throw new Exception("Invalid FormMode type!");
        }
    }

    private RepairItem getRepairItemWithFKCondition() {
        RepairItem repairItem = new RepairItem();

        repairItem.setRepair(currentRepair);

        return repairItem;
    }

    private void prepareFields() {
        repairForm.getTxtName().setText(currentRepair.getName());

        updateDerivedFields();
    }

    public void openAddRepairItemForm() throws Exception {
        Coordinator.getInstance().openAddRepairItemForm(currentRepair);
    }

    public void refreshRepairForm(RepairItem repairItem) {
        updateCurrentRepair(repairItem);
        updateRepairFormComponents();
    }

    private void updateCurrentRepair(RepairItem repairItem) {
        currentRepair.getRepairItems().add(repairItem);

        LocalDate startDate = getEarliestStartDate();
        BigDecimal riTotalRevenue = repairItem.getService().getPrice().add(repairItem.getAdditionalRevenue());
        BigDecimal riTotalExpense = repairItem.getEmployeeExpense().add(repairItem.getAdditionalExpense().add(repairItem.getService().getMaterialCost()));

        currentRepair.setStartDate(startDate);
        currentRepair.setTotalRevenue(currentRepair.getTotalRevenue().add(riTotalRevenue));
        currentRepair.setTotalExpense(currentRepair.getTotalExpense().add(riTotalExpense));
    }

    private LocalDate getEarliestStartDate() {
        LocalDate earliestDate = LocalDate.MAX;

        for (RepairItem repairItem : currentRepair.getRepairItems()) {
            if (repairItem.getStartDate() != null && repairItem.getStartDate().isBefore(earliestDate)) {
                earliestDate = repairItem.getStartDate();
            }
        }
        return earliestDate;
    }

    private void updateRepairFormComponents() {
        TableModelRepairItems tmri = (TableModelRepairItems) repairForm.getTblRepairItems().getModel();
        tmri.setRepairItems(currentRepair.getRepairItems());

        updateDerivedFields();
    }

    private void updateDerivedFields() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);//TODO: Find a better place for this!
        repairForm.getTxtStartDate().setText(currentRepair.getStartDate().format(dtf));

        repairForm.getTxtTotalRevenue().setText(String.valueOf(currentRepair.getTotalRevenue()));
        repairForm.getTxtTotalExpense().setText(String.valueOf(currentRepair.getTotalExpense()));
    }
}
