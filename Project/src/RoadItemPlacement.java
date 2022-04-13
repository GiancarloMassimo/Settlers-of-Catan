public class RoadItemPlacement extends ItemPlacement<Edge> {
    @Override
    boolean checkCondition(Edge location) {
        if (location.road != null) return false;

        Player owner1 = null;

        if (location.a.building != null) {
            owner1 = location.a.building.owner;
        }

        Player owner2 = null;

        if (location.b.building != null) {
            owner2 = location.b.building.owner;
        }

        Player currentPlayer = GameManager.instance.getCurrentPlayer();
        if (currentPlayer == owner1 || currentPlayer == owner2) {
            return true;
        }

        for (Edge adjacentEdge : location.a.getEdges()) {
            if  (adjacentEdge.road != null && adjacentEdge.road.getOwner() == currentPlayer) {
                return true;
            }
        }

        return false;
    }

    @Override
    void place(Edge location) {
        Player currentPlayer = GameManager.instance.getCurrentPlayer();
        Road newRoad = new Road(currentPlayer, location);
        location.road = newRoad;
        GameLog.instance.logEvent(currentPlayer + " placed a road between " + location.a + " and " + location.b);
    }
}
