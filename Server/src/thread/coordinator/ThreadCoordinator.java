package thread.coordinator;

import domain.Employee;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                Logger.getLogger(ThreadCoordinator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void throwIfAlreadyAuthenticated(Employee employee, String errorMessage) throws Exception {
        for (ClientHandlerThread clientHandler : clientHandlers) {
            if (clientHandler.getAuthenticatedEmployee() != null && clientHandler.getAuthenticatedEmployee().equals(employee)) {
                throw new Exception(errorMessage);
            }
        }
    }
}
