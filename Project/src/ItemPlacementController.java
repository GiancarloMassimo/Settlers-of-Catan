public class ItemPlacementController {
    private static ItemPlacer<Node> nodeItemPlacer;
    private static ItemPlacer<Edge> edgeItemPlacer;

    private static final InitialSettlementItemPlacement initialSettlementItemPlacement = new InitialSettlementItemPlacement();
    private static final RoadItemPlacement roadItemPlacement = new RoadItemPlacement();
    private static final InitialRoadItemPlacement initialRoadItemPlacement = new InitialRoadItemPlacement();
    private static final SettlementItemPlacement settlementItemPlacement = new SettlementItemPlacement();

    public static void setNodeItemPlacer(ItemPlacer<Node> placer) {
        nodeItemPlacer = placer;
    }

    public static void setEdgeItemPlacer(ItemPlacer<Edge> placer) { edgeItemPlacer = placer; }

    public static void placeInitialSettlement() {
        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> nodeItemPlacer.startItemPlacement(initialSettlementItemPlacement)
        );
    }

    public static void placeSettlement() {
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

    public static void placeRoad() {
        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> edgeItemPlacer.startItemPlacement(roadItemPlacement)
        );
    }
}
