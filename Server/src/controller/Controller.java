/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import thread.ServerThread;

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

    public void startServer() throws IOException {
        if (serverThread == null || !serverThread.isAlive()) {
            
            serverThread = new ServerThread();
            serverThread.start();
        }
    }
    
    public void stopServer() throws IOException{
        serverThread.stopThread();
    }

}
