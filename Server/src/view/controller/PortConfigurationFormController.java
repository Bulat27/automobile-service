package view.controller;

import properties.util.UtilApplicationProperties;
import validation.ValidationException;
import validation.Validator;
import view.coordinator.ViewCoordinator;
import view.form.PortConfigurationForm;

/**
 *
 * @author Dragon
 */
public class PortConfigurationFormController {

    private PortConfigurationForm portConfigurationForm;

    public PortConfigurationFormController() {
        portConfigurationForm = new PortConfigurationForm(ViewCoordinator.getInstance().getMainForm(), true, this);
    }

    public void openForm() {
        portConfigurationForm.setVisible(true);
    }

    public void closeForm() {
        portConfigurationForm.dispose();
    }

    public void save(String port) throws Exception {
        validate(port);
        UtilApplicationProperties.getInstance().setServerPort(port);
        UtilApplicationProperties.getInstance().saveChanges();
    }

    public void coordinateForms() {
        closeForm();
    }

    private void validate(String port) throws ValidationException {
        Validator.startValidation()
                .validateValueIsValidPortNumber(port, "Port number must be an integer between 1024 and 65535")
                .throwIfInvalide();
    }
}
