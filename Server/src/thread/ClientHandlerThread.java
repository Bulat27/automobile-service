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
import domain.Employee;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dragon
 */
public class ClientHandlerThread extends Thread {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private Employee employee;

    public ClientHandlerThread(Socket socket) {
        this.socket = socket;
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
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
        }
    }

    private Response handleRequest(Request request) {
        Operation operation = request.getOperation();
        
        switch (operation) {
            case LOGIN:
                return login(request);
            default:
                return null;//TODO: Maybe throw an Exception or something, not the best idea to return null
        }
    }
    
    private Response login(Request request){
        Response response = new Response();
        
//        User requestUser = (User) request.getArgument();
//        
//        try {
//            //proveri da li korisnik postoji u sistemu
//            User user = Controller.getInstance().
//                    login(requestUser.getUsername(),
//                            requestUser.getPassword());
//            System.out.println("Uspesna prijava na sistem...");
//            response.setResponseType(ResponseType.SUCCESS);
//            response.setResult(user);
//            this.user=user;//TODO: Think about whether this is a good solution!
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            response.setResponseType(ResponseType.ERROR);
//            response.setException(ex);
//        }
        return response;
    }
    
     public void stopThread() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
