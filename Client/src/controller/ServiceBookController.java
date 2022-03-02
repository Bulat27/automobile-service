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
import domain.ServiceBook;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class ServiceBookController {

    private static ServiceBookController instance;

    private ServiceBookController() {
    }

    public static ServiceBookController getInstance() {
        if (instance == null) {
            instance = new ServiceBookController();
        }
        return instance;
    }

    public void addServiceBook(ServiceBook serviceBook) throws Exception {
        Request request = new Request(Operation.ADD_SERVICE_BOOK, serviceBook);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
    }

    public List<ServiceBook> getAllServiceBooks() throws Exception {
        Request request = new Request(Operation.GET_ALL_SERVICE_BOOKS, null);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (List<ServiceBook>) response.getResult();
        }
        throw response.getException();
    }

    public List<ServiceBook> getServiceBooksByCondition(ServiceBook serviceBook) throws Exception {
        Request request = new Request(Operation.GET_SERVICE_BOOKS_BY_CONDITION, serviceBook);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (List<ServiceBook>) response.getResult();
        }
        throw response.getException();
    }

    public void deleteServiceBook(ServiceBook serviceBook) throws Exception {
        Request request = new Request(Operation.DELETE_SERVICE_BOOK, serviceBook);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
    }
}
