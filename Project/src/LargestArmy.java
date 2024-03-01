import java.util.HashMap;

public class LargestArmy {
    Player largestArmyPlayer;
    private int largestArmySize;

    private HashMap<Player, Integer> largestArmies;

    public LargestArmy() {
        largestArmyPlayer=null;
        largestArmySize=2;
        largestArmies = new HashMap<>();
        for (Player player : GameManager.instance.getPlayers()) {
            largestArmies.put(player, 0);
        }
    }
    public void checkLargestArmy(Player player, int size){
        if (size > largestArmySize) {
            if (largestArmyPlayer != null) {
                largestArmyPlayer.changePublicVictoryPoints(-2);
            }
            if (player != largestArmyPlayer) {
                player.changePublicVictoryPoints(2);
                GameLog.instance.logEvent(player + " took largest army");

            }

            largestArmySize = size;
            largestArmyPlayer = player;

            }

        if (largestArmies.get(player) < size)
            largestArmies.put(player, size);
    }

    public int getArmySize(Player player){
        return largestArmies.get(player);
    }

    public boolean hasLargestArmy(Player player) {
        return player == largestArmyPlayer;

    }

}
