public class ItemSelectionController {
    private static ItemSelectionGraphics itemSelector;

    private static final DiscardItemSelection discardItemSelection = new DiscardItemSelection();

    public static void setItemSelector(ItemSelectionGraphics itemSelector) {
        ItemSelectionController.itemSelector = itemSelector;
    }

    public static void discard(Player player) {
        GameActionHandler.queueAction(
                GameActionTypes.StartMultiStageAction,
                () -> itemSelector.startSelection(discardItemSelection, player)
        );
    }
}
