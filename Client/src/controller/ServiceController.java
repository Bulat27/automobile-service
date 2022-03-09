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
import domain.Service;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class ServiceController {

    private static ServiceController instance;

    private ServiceController() {
    }

    public static ServiceController getInstance() {
        if (instance == null) {
            instance = new ServiceController();
        }
        return instance;
    }

    public void addService(Service service) throws Exception {
        Request request = new Request(Operation.ADD_SERVICE, service);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
    }

    public List<Service> getAllServices() throws Exception {
        Request request = new Request(Operation.GET_ALL_SERVICES, null);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (List<Service>) response.getResult();
        }
        throw response.getException();
    }

    public void deleteService(Service service) throws Exception {
        Request request = new Request(Operation.DELETE_SERVICE, service);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
    }

    public List<Service> getServicesByCondition(Service s) throws Exception {
        Request request = new Request(Operation.GET_SERVICES_BY_CONDITION, s);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (List<Service>) response.getResult();
        }
        throw response.getException();
    }
}
