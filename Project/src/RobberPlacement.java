public class RobberPlacement extends ItemPlacement<Tile> {
    @Override
    boolean checkCondition(Tile location) {
        Robber robber = GameManager.instance.getRobber();
        boolean containsOpposingSettlement = false;
        for (Building building : location.buildings) {
            if (building.getOwner() != GameManager.instance.getCurrentPlayer()) {
                containsOpposingSettlement = true;
                break;
            }
        }
        return location != robber.getTile() && location.getType() != TileType.Desert && containsOpposingSettlement;
    }

    @Override
    void place(Tile location) {
        Robber robber = GameManager.instance.getRobber();
        robber.move(location);
        GameLog.instance.logEvent("Robber moved to " + location.toString());

        if (!GameManager.instance.devCardPlayed) {
            for (Player player : GameManager.instance.getPlayers()) {
                if (player.getInventory().getTotalResources() > 7) {
                    ItemSelectionController.discard(player);
                }
            }
        }

        try {
            ItemPlacementController.selectStealingLocation();
        }
        catch (NoValidPositionForItemException e) {
            // simply do nothing
        }

        System.out.println();
    }
}
