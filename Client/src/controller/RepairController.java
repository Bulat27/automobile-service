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
import domain.Repair;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class RepairController {

    private static RepairController instance;

    private RepairController() {
    }

    public static RepairController getInstance() {
        if (instance == null) {
            instance = new RepairController();
        }
        return instance;
    }

    public List<Repair> getRepairsByFKCondition(Repair repair) throws Exception {
        Request request = new Request(Operation.GET_REPAIRS_BY_FK_CONDITION, repair);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (List<Repair>) response.getResult();
        }
        throw response.getException();
    }
}