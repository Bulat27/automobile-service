/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread.coordinator;

import domain.Employee;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import thread.ClientHandlerThread;

/**
 *
 * @author Dragon
 */
public class ThreadCoordinator {

    private static ThreadCoordinator instance;

    private List<ClientHandlerThread> clientHandlers;

    private ThreadCoordinator() {
        this.clientHandlers = new ArrayList();
    }

    public static ThreadCoordinator getInstance() {
        if (instance == null) {
            instance = new ThreadCoordinator();
        }
        return instance;
    }

    public void addClientHandlerThread(ClientHandlerThread clientHandlerThread) {
        clientHandlers.add(clientHandlerThread);
    }

    public void removeClientHandlerThread(ClientHandlerThread clientHandlerThread) {
        clientHandlers.remove(clientHandlerThread);
    }

    public void stopAllClientHandlerThreads() {
        for (ClientHandlerThread clientHandler : clientHandlers) {
            try {
                clientHandler.stopThread();
            } catch (IOException ex) {
                ex.printStackTrace();//TODO: Think about this handling here! Is a Logger enough or something else is neccessary?
            }
        }
    }

    public void checkIfAlreadyAuthenticated(Employee employee) throws Exception {
        for (ClientHandlerThread clientHandler : clientHandlers) {
            if (clientHandler.getAuthenticatedEmployee() != null && clientHandler.getAuthenticatedEmployee().equals(employee)) {
                throw new Exception("Employee with username: " + employee.getUsername() + " is already logged in.");
            }
        }
    }
}
