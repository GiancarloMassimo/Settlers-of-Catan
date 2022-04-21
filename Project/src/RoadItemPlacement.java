public class RoadItemPlacement extends ItemPlacement<Edge> {
    @Override
    boolean checkCondition(Edge location) {
        if (location.road != null) return false;
        Player currentPlayer = GameManager.instance.getCurrentPlayer();

        Player owner1 = null;

        if (location.a.building != null) {
            owner1 = location.a.building.owner;
            if (owner1 != currentPlayer) {
                for (Edge adjacentEdge : location.b.getEdges()) {
                    if  (adjacentEdge.road != null && adjacentEdge.road.getOwner() == currentPlayer) {
                        return true;
                    }
                }
                return false;
            }
        }

        Player owner2 = null;

        if (location.b.building != null) {
            owner2 = location.b.building.owner;
            if (owner2 != currentPlayer) {
                for (Edge adjacentEdge : location.a.getEdges()) {
                    if  (adjacentEdge.road != null && adjacentEdge.road.getOwner() == currentPlayer) {
                        return true;
                    }
                }
                return false;
            }
        }

        if (currentPlayer == owner1 || currentPlayer == owner2) {
            return true;
        }

        for (Edge adjacentEdge : location.a.getEdges()) {
            if  (adjacentEdge.road != null && adjacentEdge.road.getOwner() == currentPlayer) {
                return true;
            }
        }

        for (Edge adjacentEdge : location.b.getEdges()) {
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
        GameManager.instance.getLongestRoad().measureLongestRoad(newRoad);
        GameLog.instance.logEvent(currentPlayer + " placed a road between " + location.a + " and " + location.b);

        //currentPlayer.addRoad(newRoad);
    }
}
