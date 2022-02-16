/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Employee;
import domain.Service;
import java.io.IOException;
import system_operation.AbstractSO;
import system_operation.login.LoginSO;
import system_operation.service.SaveServiceSO;
import thread.ServerThread;
import view.form.MainForm;

/**
 *
 * @author Dragon
 */
public class Controller {

    private static Controller instance;
    private ServerThread serverThread;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void startServer(MainForm mainForm) throws IOException {
        if (serverThread == null || !serverThread.isAlive()) {
            
            serverThread = new ServerThread(mainForm);
            serverThread.start();
        }
    }
    
    public void stopServer() throws IOException{
        serverThread.stopThread();
    }

    public Employee login(Employee requestEmployee) throws Exception {
        LoginSO loginSO = new LoginSO();//TODO: Once you put getResult in AbstractSO, then use AbstractSO here and only cast in returu e.g.
        //return (Employee) loginSO.getResult();
        loginSO.execute(requestEmployee);
        
        return (Employee) loginSO.getResult();
    }

    public void saveService(Service service) throws Exception{
        SaveServiceSO saveServiceSO = new SaveServiceSO();
        saveServiceSO.execute(service);
    }

}
