package communication;

import communication.util.Operation;
import java.io.Serializable;

/**
 *
 * @author Dragon
 */
public class Request implements Serializable {

    private Operation operation;
    private Object argument;

    public Request() {
    }

    public Request(Operation operation, Object argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }
}
