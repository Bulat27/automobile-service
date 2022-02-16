/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import view.form.MainForm;

/**
 *
 * @author Dragon
 */
public class MainFormController {

    private MainForm mainForm;

    public MainFormController() {
        mainForm = new MainForm(this);
    }

    public void openForm() {//TODO: You could add interface for FormControllers
        mainForm.setVisible(true);
    }

    public void closeForm() {
        mainForm.dispose();
    }
}
