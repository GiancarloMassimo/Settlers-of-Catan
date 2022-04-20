public class StealingFromSettlementChoicePlacement extends ItemPlacement<Node> {
    @Override
    boolean checkCondition(Node location) {
        Robber robber = GameManager.instance.getRobber();

        if (location.getBuilding() == null) return false;
        if (location.getBuilding().getOwner() == GameManager.instance.getCurrentPlayer()) return false;
        if (location.getAdjacentTiles().contains(robber.getTile())) return true;

        return false;
    }

    @Override
    void place(Node location) {
        Player currentPlayer = GameManager.instance.getCurrentPlayer();
        ResourceType resourceStolen = currentPlayer.stealFromPlayer(location.building.getOwner().getInventory());

        GameLog.instance.logEvent(currentPlayer +
                " stole a " + (resourceStolen == null ? " nothing " : resourceStolen.toString()) +
                " from " + location.building.owner);
    }
}
