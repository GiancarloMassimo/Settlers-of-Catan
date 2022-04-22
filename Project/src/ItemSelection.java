import java.util.HashMap;

public interface ItemSelection {
    void setUp(Player player);
    String getSelectionMessage();
    void tryItemSelection(ResourceType resourceType);
    boolean isCompleteSelection();
    void clearSelection();
    void confirm();
    HashMap<ResourceType, Integer> getSelection();
}
