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
        Player currentPlayer = GameManager.instance.getCurrentPlayer();
        Building building = new Building(currentPlayer, location);
        for (Tile tile : location.getAdjacentTiles()) {
            tile.buildings.add(building);
        }
        currentPlayer.addBuilding(building);
        location.building = building;
        GameLog.instance.logEvent(building.getOwner() + " placed a " + building.getType() + " at " + location);
    }
}
