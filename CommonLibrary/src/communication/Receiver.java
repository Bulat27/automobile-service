/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Dragon
 */
public class Receiver {

    private final Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public Object receive() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();//TODO: Obrisi ovo!
            throw new Exception("Error receiving object:" + ex.getMessage());
        }
    }
}
