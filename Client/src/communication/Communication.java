/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.net.Socket;
import view.coordinator.Coordinator;
import view.form.LoginForm;

/**
 *
 * @author Dragon
 */
public class Communication {

    private static Communication instance;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private boolean connected = false;

    private Communication() {
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public void connect() throws IOException {//TODO: Reasses this way of handling Exception. Maybe, a server form opens and if the connection
        //fails, than you can show JOptionPane and close everything.
        socket = new Socket("127.0.0.1", 9000); //TODO: This needs to be read from a file!
        System.out.println("Client is connected!");
        sender = new Sender(socket);
        receiver = new Receiver(socket);
        connected = true;
//        new LoginForm().setVisible(true);//TODO: This can be done by some Coordinator or something, we will se about that!
        Coordinator.getInstance().openLoginForm();
    }

    public Response sendRequest(Request request) throws Exception  {
        if(!connected) throw new Exception("Request cannot be send before a connection has been made!");
//TODO: Make a custom Exception for this!
        
        sender.send(request);
        System.out.println("Request has been sent...");
        return (Response) receiver.receive();
    }
}
