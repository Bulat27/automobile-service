package view.controller;

import communication.Communication;
import static domain.util.EmployeeRole.WORKER;
import view.coordinator.Coordinator;
import view.form.MainForm;

/**
 *
 * @author Dragon
 */
public class MainFormController {

    private MainForm mainForm;

    public MainForm getMainForm() {
        return mainForm;
    }

    public MainFormController() {
        mainForm = new MainForm(this);
    }

    public void openForm() {
        prepareForm();
        mainForm.setVisible(true);
    }

    public void closeForm() {
        mainForm.dispose();
    }

    public void openServiceForm() {
        Coordinator.getInstance().openServiceForm();
    }

    public void openShowServicesForm() throws Exception {
        Coordinator.getInstance().openShowServicesForm();
    }

    public void openAddEmployeeForm() {
        Coordinator.getInstance().openAddEmployeeForm();
    }

    public void openShowEmployeesForm() throws Exception {
        Coordinator.getInstance().openShowEmployeesForm();
    }

    private void prepareForm() {
        if (Communication.getInstance().getAuthenticatedEmployee().getEmployeeRole() == WORKER) {
            mainForm.getMenuEmployee().setEnabled(false);
        }

        mainForm.getLblWelcome().setText("Welcome " + Communication.getInstance().getAuthenticatedEmployee().getUsername());
    }

    public void openAddServiceBookForm() {
        Coordinator.getInstance().openAddServiceBookForm();
    }

    public void openShowServiceBooksForm() throws Exception {
        Coordinator.getInstance().openShowServiceBooksForm();
    }
}
