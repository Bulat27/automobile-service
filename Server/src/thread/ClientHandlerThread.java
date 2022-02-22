/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import communication.util.Operation;
import communication.util.ResponseType;
import controller.Controller;
import domain.Employee;
import domain.Service;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.form.MainForm;

/**
 *
 * @author Dragon
 */
public class ClientHandlerThread extends Thread {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private Employee authenticatedEmployee;
    private final MainForm mainForm;

    public ClientHandlerThread(Socket socket, MainForm mainForm) {
        this.socket = socket;
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
        this.mainForm = mainForm;
    }

    @Override
    public void run() {
        try { //TODO:I've made some changes here, make sure everything works fine!
            while (!socket.isClosed()) {

                Request request = (Request) receiver.receive();
                Response response = handleRequest(request);
                sender.send(response);

            }
        } catch (Exception ex) {
            Logger.getLogger(ClientHandlerThread.class.getName()).log(Level.SEVERE, null, ex);//TODO: Don't just log it, find a way to notify the
            //user of what happened.
            mainForm.removeEmployee(authenticatedEmployee);//TODO: You could also remove this ClientHandlerThread from the list!

        }
    }

    private Response handleRequest(Request request) {
        Operation operation = request.getOperation();

        switch (operation) {
            case LOGIN:
                return login(request);
            case SAVE_SERVICE:
                return saveService(request);
            case GET_ALL_SERVICES:
                return getAllServices(request);
            case DELETE_SERVICE:
                return deleteService(request);    
//            case LOGOUT:
//                return logout(request);    
            default:
                return null;//TODO: Maybe throw an Exception or something, not the best idea to return null
        }
    }

    private Response login(Request request) {
        Response response = new Response();

        Employee requestEmployee = (Employee) request.getArgument();

        try {
            Employee employee = Controller.getInstance().login(requestEmployee);
            System.out.println("Successful authentication!");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(employee);
            this.authenticatedEmployee = employee;//TODO: Think about whether this is a good solution!
            mainForm.addEmployee(authenticatedEmployee);
        } catch (Exception ex) {
            ex.printStackTrace();//TODO: Delete this!
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;
    }

    public void stopThread() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

//    private Response logout(Request request) {
//        Response response = new Response();
//        
////        try {       
////            Employee employee = Controller.getInstance().login(service);
////            System.out.println("Successful authentication!");
////            response.setResponseType(ResponseType.SUCCESS);
////            response.setResult(employee);
////            this.authenticatedEmployee = employee;//TODO: Think about whether this is a good solution!
////            mainForm.addEmployee(employee);
////        } catch (Exception ex) {
////            ex.printStackTrace();//TODO: Delete this!
////            response.setResponseType(ResponseType.ERROR);
////            response.setException(ex);
////        }
//        
//        return response;
//    }
    private Response saveService(Request request) {
        Response response = new Response();

        Service service = (Service) request.getArgument();

        try {
            Controller.getInstance().saveService(service);
            System.out.println("Successfully saved Service!");
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception ex) {
            ex.printStackTrace();//TODO: Delete this!
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;
    }

    private Response getAllServices(Request request) {
        Response response = new Response();

        try {
            List<Service> services = Controller.getInstance().getAllServices();
            System.out.println("Successful retrieval of services!");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(services);
        } catch (Exception ex) {
            ex.printStackTrace();//TODO: Delete this!
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;
    }

    private Response deleteService(Request request) {
        Response response = new Response();

        Service service = (Service) request.getArgument();

        try {
            Controller.getInstance().deleteService(service);
            System.out.println("Successfully deleted Service!");
            response.setResponseType(ResponseType.SUCCESS);
        } catch (Exception ex) {
            ex.printStackTrace();//TODO: Delete this!
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;
    }
}
