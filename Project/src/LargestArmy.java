public class LargestArmy {
    private Player largestArmyPlayer;
    private int largestArmySize;

    public LargestArmy(){
        largestArmyPlayer=null;
        largestArmySize=2;
    }

    public void checkLargestArmy(Player player, int size){
        if (size > largestArmySize) {
            if (largestArmyPlayer != null) {
                largestArmyPlayer.changePublicVictoryPoints(-2);
            }
            if (player != largestArmyPlayer) {
                player.changePublicVictoryPoints(2);
            }

            largestArmySize = size;
            largestArmyPlayer = player;
            }
    }


}
