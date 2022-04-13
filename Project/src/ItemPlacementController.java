public class ItemPlacementController {
    private static ItemPlacer<Node> nodeItemPlacer;
    private static InitialSettlementItemPlacement initialSettlementItemPlacement = new InitialSettlementItemPlacement();

    public static void setNodeItemPlacer(ItemPlacer<Node> placer) {
        nodeItemPlacer = placer;
    }

    public static void placeInitialSettlement() {
        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                (Object... params) -> nodeItemPlacer.startItemPlacement(initialSettlementItemPlacement),
                null
        );

    }
}
