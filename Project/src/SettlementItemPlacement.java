public class SettlementItemPlacement extends InitialSettlementItemPlacement {

    @Override
    public boolean checkCondition(Node location) {
        if (!super.checkCondition(location)) return false;

        for (Edge edge : location.getEdges()) {
            if (edge.road != null && edge.road.getOwner() == GameManager.instance.getCurrentPlayer()) {
                return true;
            }
        }

        return false;
    }
}
