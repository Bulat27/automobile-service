/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private List<ClientHandlerThread> clients;

    public ServerThread() throws IOException {//TODO: Make sure that it is handled. Some JOptionPane...
        serverSocket = new ServerSocket(9000);//TODO: This needs to be read out of the configuration
        clients = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            try {
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                ClientHandlerThread thread = new ClientHandlerThread(socket);
                thread.start();
                clients.add(thread);
                System.out.println("Client connected!");
            } catch (IOException ex) {
                ex.printStackTrace();//TODO: Think about this handling here! Is a Logger enough or something else is neccessary?
            }
        }
        stopAllClientThreads();
    }

    public void stopThread() throws IOException {
        if (serverSocket != null && serverSocket.isBound()) {//TODO: Check this out! Should i call stopAll() here or leave it as it is? Read some more docs about isBound()
            serverSocket.close();
        }
    }

    private void stopAllClientThreads() {
        for (ClientHandlerThread client : clients) {
            try {
                client.stopThread();
            } catch (IOException ex) {
                ex.printStackTrace();//TODO: Think about this handling here! Is a Logger enough or something else is neccessary?
            }
        }
    }

}
