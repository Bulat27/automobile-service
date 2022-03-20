
package controller;

import communication.Communication;
import communication.Request;
import communication.Response;
import communication.util.Operation;
import communication.util.ResponseType;
import domain.RepairItem;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class RepairItemController {

    private static RepairItemController instance;

    private RepairItemController() {
    }

    public static RepairItemController getInstance() {
        if (instance == null) {
            instance = new RepairItemController();
        }
        return instance;
    }

    public List<RepairItem> getRepairItemsByFKCondition(RepairItem repairItem) throws Exception {
        Request request = new Request(Operation.GET_REPAIR_ITEMS_BY_FK_CONDITION, repairItem);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (List<RepairItem>) response.getResult();
        }
        throw response.getException();
    }
}
