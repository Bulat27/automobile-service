/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coordinator;

import domain.Employee;
import domain.Repair;
import domain.ServiceBook;
import view.controller.EmployeeFormController;
import view.controller.ServiceFormController;
import view.controller.LoginFormController;
import view.controller.MainFormController;
import view.controller.RepairFormController;
import view.controller.ServiceBookFormController;
import view.controller.ShowEmployeesFormController;
import view.controller.ShowRepairsFormController;
import view.controller.ShowServiceBooksFormController;
import view.controller.ShowServicesFormController;
import view.form.MainForm;
import view.form.ShowRepairsForm;
import view.form.ShowServiceBooksForm;
import static view.util.FormMode.ADD;
import static view.util.FormMode.EDIT;

/**
 *
 * @author Dragon
 */
public class Coordinator {

    private static Coordinator instance;

    private LoginFormController loginController;
    private MainFormController mainFormController;
    private ServiceFormController serviceFormController;
    private ShowServicesFormController showServicesFormController;
    private EmployeeFormController employeeFormController;
    private ShowEmployeesFormController showEmployeesFormController;
    private ServiceBookFormController serviceBookFormController;
    private ShowServiceBooksFormController showServiceBooksFormController;
    private ShowRepairsFormController showRepairsFormController;
    private RepairFormController repairFormController;

    private Coordinator() {
    }

    public static Coordinator getInstance() {
        if (instance == null) {
            instance = new Coordinator();
        }
        return instance;
    }

    public void openLoginForm() {
        loginController = new LoginFormController();
        loginController.openForm();
    }

//    public void closeLoginForm() {
//        loginController.closeForm();
//    }
    public void openMainForm() {
        mainFormController = new MainFormController();
        mainFormController.openForm();
    }

    public void openServiceForm() {
        serviceFormController = new ServiceFormController();
        serviceFormController.openForm();
    }

    public MainForm getMainForm() {
        return mainFormController.getMainForm();
    }

    public ShowServiceBooksForm getShowServiceBooksForm() {
        return showServiceBooksFormController.getShowServiceBooksForm();
    }

    public ShowRepairsForm getShowRepairsForm() {
        return showRepairsFormController.getShowRepairsForm();
    }

    public void openShowServicesForm() throws Exception {
        showServicesFormController = new ShowServicesFormController();
        showServicesFormController.openForm();
    }

    public void openAddEmployeeForm() {
        employeeFormController = new EmployeeFormController(ADD, null, -1);
        employeeFormController.openForm();
    }

    public void openShowEmployeesForm() throws Exception {
        showEmployeesFormController = new ShowEmployeesFormController();
        showEmployeesFormController.openForm();
    }

    public void openEditEmployeeForm(Employee employee, int selectedRow) {
        employeeFormController = new EmployeeFormController(EDIT, employee, selectedRow);
        employeeFormController.openForm();
    }

    public void refreshShowEmployeesForm(Employee employee, int selectedRow) {
        showEmployeesFormController.refreshShowEmployeesForm(employee, selectedRow);
    }

    public void openAddServiceBookForm() {
        serviceBookFormController = new ServiceBookFormController(ADD, null, -1);
        serviceBookFormController.openForm();
    }

    public void openShowServiceBooksForm() throws Exception {
        showServiceBooksFormController = new ShowServiceBooksFormController();
        showServiceBooksFormController.openForm();
    }

    public void openEditServiceBookForm(ServiceBook serviceBook, int selectedRow) {
        serviceBookFormController = new ServiceBookFormController(EDIT, serviceBook, selectedRow);
        serviceBookFormController.openForm();
    }

    public void refreshShowServiceBooksForm(ServiceBook serviceBook, int selectedRow) {
        showServiceBooksFormController.refreshShowServiceBooksForm(serviceBook, selectedRow);
    }

    public void openShowRepairsForm(ServiceBook serviceBook, int selectedRow) throws Exception {
        showRepairsFormController = new ShowRepairsFormController(serviceBook, selectedRow);
        showRepairsFormController.openForm();
    }

    public void openAddRepairForm() throws Exception {
        repairFormController = new RepairFormController(ADD, null, -1);
        repairFormController.openForm();
    }

    public void openEditRepairForm(Repair repair, int selectedRow) throws Exception {
        repairFormController = new RepairFormController(EDIT, repair, selectedRow);
        repairFormController.openForm();
    }
}
