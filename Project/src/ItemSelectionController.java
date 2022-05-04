public class ItemSelectionController {
    private static ItemSelectionGraphics itemSelector;

    private static final DiscardItemSelection discardItemSelection = new DiscardItemSelection();
    private static final MonopolyItemSelection monopolyItemSelection = new MonopolyItemSelection();
    private static final YearOfPlentyItemSelection yearOfPlentyItemSelection = new YearOfPlentyItemSelection();

    public static void setItemSelector(ItemSelectionGraphics itemSelector) {
        ItemSelectionController.itemSelector = itemSelector;
    }

    public static void discard(Player player) {
        GameActionHandler.queueAction(
                GameActionTypes.StartMultiStageAction,
                () -> itemSelector.startSelection(discardItemSelection, player)
        );
    }

    public static void monopoly(Player player) {
        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> itemSelector.startSelection(monopolyItemSelection, player)
        );
    }

    public static void yearOfPlenty(Player player) {
        GameActionHandler.signalAction(
                GameActionTypes.StartMultiStageAction,
                () -> itemSelector.startSelection(yearOfPlentyItemSelection, player)
        );
    }
}
