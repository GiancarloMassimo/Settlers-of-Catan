public class RobberPlacement extends ItemPlacement<Tile> {
    @Override
    boolean checkCondition(Tile location) {
        Robber robber = GameManager.instance.getRobber();
        return location != robber.getTile();
    }

    @Override
    void place(Tile location) {
        Robber robber = GameManager.instance.getRobber();
        robber.move(location);
        GameLog.instance.logEvent("Robber moved to " + location.toString());
    }
}
