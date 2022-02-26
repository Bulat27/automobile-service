/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Employee;
import domain.Service;
import java.io.IOException;
import java.util.List;
import system_operation.AbstractSO;
import system_operation.employee.GetAllEmployeesSO;
import system_operation.employee.GetEmployeesByConditionSO;
import system_operation.employee.SaveEmployeeSO;
import system_operation.login.LoginSO;
import system_operation.service.DeleteServiceSO;
import system_operation.service.GetAllServicesSO;
import system_operation.service.GetServicesByConditionSO;
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

    public void stopServer() throws IOException {
        serverThread.stopThread();
    }

    public Employee login(Employee requestEmployee) throws Exception {
        LoginSO loginSO = new LoginSO();//TODO: Once you put getResult in AbstractSO, then use AbstractSO here and only cast in returu e.g.
        //return (Employee) loginSO.getResult();
        loginSO.execute(requestEmployee);

        return (Employee) loginSO.getResult();
    }

    public void saveService(Service service) throws Exception {
        SaveServiceSO saveServiceSO = new SaveServiceSO();
        saveServiceSO.execute(service);
    }

    public List<Service> getAllServices() throws Exception {
        GetAllServicesSO getAllServicesSO = new GetAllServicesSO();
        getAllServicesSO.execute(null);

        return (List<Service>) getAllServicesSO.getResult();
    }

    public void deleteService(Service service) throws Exception {
        DeleteServiceSO deleteServiceSO = new DeleteServiceSO();
        deleteServiceSO.execute(service);
    }

    public List<Service> getServicesByCondition(Service service) throws Exception {
        GetServicesByConditionSO getServicesByConditionSO = new GetServicesByConditionSO();
        getServicesByConditionSO.execute(service);

        return (List<Service>) getServicesByConditionSO.getResult();
    }

    public void saveEmployee(Employee employee) throws Exception {
        SaveEmployeeSO saveEmployeeSO = new SaveEmployeeSO();
        saveEmployeeSO.execute(employee);
    }

    public List<Employee> getAllEmployees() throws Exception {
        GetAllEmployeesSO getAllEmployeesSO = new GetAllEmployeesSO();
        getAllEmployeesSO.execute(null);

        return (List<Employee>) getAllEmployeesSO.getResult();
    }

    public List<Employee> getEmployeesByCondition(Employee employee) throws Exception {
        GetEmployeesByConditionSO getEmployeesByConditionSO = new GetEmployeesByConditionSO();
        getEmployeesByConditionSO.execute(employee);

        return (List<Employee>) getEmployeesByConditionSO.getResult();
    }
}
