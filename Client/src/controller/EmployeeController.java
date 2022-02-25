/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Communication;
import communication.Request;
import communication.Response;
import communication.util.Operation;
import communication.util.ResponseType;
import domain.Employee;

/**
 *
 * @author Dragon
 */
public class EmployeeController {

    private static EmployeeController instance;

    private EmployeeController() {
    }

    public static EmployeeController getInstance() {
        if (instance == null) {
            instance = new EmployeeController();
        }
        return instance;
    }

    public Employee login(Employee employee) throws Exception {
        Request request = new Request(Operation.LOGIN, employee);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (Employee) response.getResult();
        }
        throw response.getException();
    }

//    public void logOut() throws Exception {
//        Request request = new Request(Operation.LOGIN, null);
//        Response response = Communication.getInstance().sendRequest(request);
//
//        if (response.getResponseType().equals(ResponseType.ERROR)) {
//            throw response.getException();
//        }
//    }
    public void saveEmployee(Employee employee) throws Exception {
        Request request = new Request(Operation.SAVE_EMPLOYEE, employee);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
    }
}
