
public class ItemPlacementController {
    private static ItemPlacer<Node> nodeItemPlacer;
    private static ItemPlacer<Edge> edgeItemPlacer;
    private static ItemPlacer<Tile> tileItemPlacer;

    private static final InitialSettlementItemPlacement initialSettlementItemPlacement = new InitialSettlementItemPlacement();
    private static final RoadItemPlacement roadItemPlacement = new RoadItemPlacement();
    private static final InitialRoadItemPlacement initialRoadItemPlacement = new InitialRoadItemPlacement();
    private static final SettlementItemPlacement settlementItemPlacement = new SettlementItemPlacement();
    private static final CityItemPlacement cityItemPlacement = new CityItemPlacement();
    private static final RobberPlacement robberPlacement = new RobberPlacement();
    private static final StealingFromSettlementChoicePlacement stealingFromSettlementChoicePlacement = new StealingFromSettlementChoicePlacement();

    public static void setNodeItemPlacer(ItemPlacer<Node> placer) {
        nodeItemPlacer = placer;
    }

    public static void setEdgeItemPlacer(ItemPlacer<Edge> placer) { edgeItemPlacer = placer; }

    public static void setTileItemPlacer(ItemPlacer<Tile> placer) { tileItemPlacer = placer; }

    public static void placeInitialSettlement() {
        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> nodeItemPlacer.startItemPlacement(initialSettlementItemPlacement)
        );
    }

    public static void placeSettlement() throws NoValidPositionForItemException {
        if (!settlementItemPlacement.validPositionExists(GameManager.instance.getMap().getNodes()))
            throw new NoValidPositionForItemException();

        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> nodeItemPlacer.startItemPlacement(settlementItemPlacement)
        );
    }

    public static void placeInitialRoad() {
        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> edgeItemPlacer.startItemPlacement(initialRoadItemPlacement)
        );
    }

    public static void placeRoad() throws NoValidPositionForItemException{
        if (!roadItemPlacement.validPositionExists(GameManager.instance.getMap().getEdges()))
            throw new NoValidPositionForItemException();

        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> edgeItemPlacer.startItemPlacement(roadItemPlacement)
        );
    }

    public static void placeCity() throws NoValidPositionForItemException {
        if (!cityItemPlacement.validPositionExists(GameManager.instance.getMap().getNodes()))
            throw new NoValidPositionForItemException();

        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> nodeItemPlacer.startItemPlacement(cityItemPlacement)
        );
    }

    public static void selectStealingLocation() throws NoValidPositionForItemException {
        if (!stealingFromSettlementChoicePlacement.validPositionExists(GameManager.instance.getMap().getNodes()))
            throw new NoValidPositionForItemException();

        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> nodeItemPlacer.startItemPlacement(stealingFromSettlementChoicePlacement)
        );
    }

    public static void placeRobber() {
        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> tileItemPlacer.startItemPlacement(robberPlacement)
        );
    }
}
