public class GameActionHandler {
    private static boolean takingAction = false;

    public static void signalAction(GameActionTypes type, GameActionOperator action) {
        if (canTakeAction(type)) {
            processActionType(type);
            action.TakeAction();
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
