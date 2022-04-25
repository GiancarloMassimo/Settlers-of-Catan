import java.util.LinkedList;
import java.util.Queue;

public class GameActionHandler {
    private static boolean takingAction = false;

    private static Queue<GameActionTypes> actionTypesQueue = new LinkedList<>();
    private static Queue<GameActionOperator> actionOperatorQueue = new LinkedList<>();

    public static void signalAction(GameActionTypes type, GameActionOperator action) {
        if (canTakeAction(type)) {
            processActionType(type);
            action.TakeAction();
            if (type == GameActionTypes.EndMultiStageAction) {
                dequeueAction();
            }
        }
    }

    public static void queueAction(GameActionTypes type, GameActionOperator action) {
        actionTypesQueue.add(type);
        actionOperatorQueue.add(action);
        if (canTakeAction(type) && actionTypesQueue.isEmpty()) {
            dequeueAction();
        }
    }

    private static void dequeueAction() {
        if (!actionTypesQueue.isEmpty()) {
            signalAction(actionTypesQueue.remove(), actionOperatorQueue.remove());
        }
    }

    private static boolean canTakeAction(GameActionTypes type) {
        if (type == GameActionTypes.Instant || type == GameActionTypes.StartMultiStageAction) {
            return !takingAction;
        } else if (type == GameActionTypes.EndMultiStageAction) {
            return takingAction;
        }
        return false;
    }

    private static void processActionType(GameActionTypes type) {
        switch (type) {
            case Instant:
                break;
            case StartMultiStageAction:
                takingAction = true;
                break;
            case EndMultiStageAction:
                takingAction = false;
                break;
        }
    }
}
