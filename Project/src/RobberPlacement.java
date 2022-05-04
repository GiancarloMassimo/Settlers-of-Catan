public class RobberPlacement extends ItemPlacement<Tile> {
    @Override
    boolean checkCondition(Tile location) {
        Robber robber = GameManager.instance.getRobber();
        return location != robber.getTile() && location.getType() != TileType.Desert;
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
