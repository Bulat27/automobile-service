/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.RepairItemController;
import domain.Repair;
import domain.RepairItem;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import view.coordinator.Coordinator;
import view.form.RepairForm;
import view.form.model.TableModelRepairItems;
import view.util.FormMode;
import static view.util.FormMode.EDIT;

/**
 *
 * @author Dragon
 */
public class RepairFormController {

    private RepairForm repairForm;
    private FormMode formMode;
    private Repair selectedRepair;
    private int selectedRow;

    private static final String DATE_PATTERN = "dd.MM.yyyy";//TODO: Find a better place for this!

    public RepairFormController(FormMode formMode, Repair selectedRepair, int selectedRow) {
        this.formMode = formMode;
        this.selectedRepair = selectedRepair;
        this.selectedRow = selectedRow;
        this.repairForm = new RepairForm(Coordinator.getInstance().getShowRepairsForm(), true, this);
    }

    public void openForm() throws Exception {
        prepareForm();
        repairForm.setVisible(true);
    }

    private void prepareForm() throws Exception {
        prepareTable();

        if (formMode == EDIT && selectedRepair != null) {
            prepareFields();
        }
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

                selectedRepair.setRepairItems(repairItems);

                return repairItems;

            default:
                throw new Exception("Invalid FormMode type!");
        }
    }

    private RepairItem getRepairItemWithFKCondition() {
        RepairItem repairItem = new RepairItem();

        repairItem.setRepair(selectedRepair);

        return repairItem;
    }

    private void prepareFields() {
        repairForm.getTxtName().setText(selectedRepair.getName());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);//TODO: Find a better place for this!
        repairForm.getTxtStartDate().setText(selectedRepair.getStartDate().format(dtf));

        repairForm.getTxtTotalRevenue().setText(String.valueOf(selectedRepair.getTotalRevenue()));
        repairForm.getTxtTotalExpense().setText(String.valueOf(selectedRepair.getTotalExpense()));
    }
}
