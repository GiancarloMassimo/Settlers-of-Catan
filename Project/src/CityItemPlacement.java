public class CityItemPlacement extends ItemPlacement<Node> {
    @Override
    boolean checkCondition(Node location) {
        return location.building != null &&
                location.building.owner == GameManager.instance.getCurrentPlayer() &&
                location.building.getType() == BuildingType.Settlement;
    }

    @Override
    void place(Node location) {
        location.building.upgrade();
        GameLog.instance.logEvent(GameManager.instance.getCurrentPlayer() + " built a city on " + location);
    }
}
