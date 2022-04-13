public class InitialSettlementItemPlacement extends ItemPlacement<Node> {
    @Override
    public boolean checkCondition(Node location) {
        if (location.building != null) return false;

        for (Edge edge : location.getEdges()) {
            if (edge.getAdjacentNode(location).building != null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void place(Node location) {
        Building building = new Building(GameManager.instance.getCurrentPlayer(), location);
        location.building = building;
        GameLog.instance.logEvent(building.getOwner() + " placed a " + building.getType() + " at " + location);
    }
}
