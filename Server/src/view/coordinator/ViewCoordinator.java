package view.coordinator;

import domain.Employee;
import view.controller.DatabaseConfigurationFormController;
import view.controller.MainFormController;
import view.controller.PortConfigurationFormController;
import view.form.MainForm;
import view.util.RefreshMode;

/**
 *
 * @author Dragon
 */
public class ViewCoordinator {

    private static ViewCoordinator instance;

    private MainFormController mainFormController;
    private PortConfigurationFormController portConfigurationFormController;
    private DatabaseConfigurationFormController databaseConfigurationFormController;

    private ViewCoordinator() {
    }

    public static ViewCoordinator getInstance() {
        if (instance == null) {
            instance = new ViewCoordinator();
        }
        return instance;
    }

    public MainForm getMainForm() {
        return mainFormController.getMainForm();
    }

    public void openMainForm() {
        mainFormController = new MainFormController();
        mainFormController.openForm();
    }

    public void refreshMainForm(Employee employee, RefreshMode refreshMode) {
        mainFormController.refreshForm(employee, refreshMode);
    }

    public void openPortConfigurationForm() {
        portConfigurationFormController = new PortConfigurationFormController();
        portConfigurationFormController.openForm();
    }

    public void openDatabaseConfigurationForm() {
        databaseConfigurationFormController = new DatabaseConfigurationFormController();
        databaseConfigurationFormController.openForm();
    }
}
