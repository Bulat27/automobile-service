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
import properties.util.UtilApplicationProperties;
import view.form.MainForm;

/**
 *
 * @author Dragon
 */
public class ServerThread extends Thread {

    private final ServerSocket serverSocket;
    private List<ClientHandlerThread> clients;
    private final MainForm mainForm;//TODO: This can probably be done some other way!

    public ServerThread(MainForm mainForm) throws IOException {//TODO: Make sure that it is handled. Some JOptionPane...
        int serverPort = UtilApplicationProperties.getInstance().getServerPort();

        this.serverSocket = new ServerSocket(serverPort);//TODO: This needs to be read out of the configuration
        this.clients = new ArrayList<>();
        this.mainForm = mainForm;
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            try {
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                ClientHandlerThread thread = new ClientHandlerThread(socket, mainForm);
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
