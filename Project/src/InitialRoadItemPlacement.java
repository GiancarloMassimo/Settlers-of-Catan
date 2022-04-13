public class InitialRoadItemPlacement extends RoadItemPlacement {
    @Override
    boolean checkCondition(Edge location) {
        if (location.road != null) return false;

        Player currentPlayer = GameManager.instance.getCurrentPlayer();
        Node locationOfLastBuildingPlaced = currentPlayer.getLastBuildingPlaced().getLocation();

        for (Edge edge : locationOfLastBuildingPlaced.getEdges()) {
            if (location == edge) {
                return true;
            }
        }

        return false;
    }

    @Override
    void place(Edge location) {
        super.place(location);
        if (GameManager.instance.getTurnCount() == GameManager.instance.getInitialTurns() - 1) {
            GameManager.instance.getDice().rollDice();
        }
    }
}
