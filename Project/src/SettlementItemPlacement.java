public class SettlementItemPlacement extends InitialSettlementItemPlacement {
    //TODO: DON'T ALLOW SOMEONE TO BUY A BUILDING THAT THEY CANT PLACE

    @Override
    public boolean checkCondition(Node location) {
        if (!super.checkCondition(location)) return false;

        for (Edge edge : location.getEdges()) {
            if (edge.road != null && edge.road.getOwner() == GameManager.instance.getCurrentPlayer()) {
                return true;
            }
        }

        return true;
    }
}
