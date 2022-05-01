public class Dice {
    private int die1 = 1, die2 = 1;

    public void rollDice() {
        die1 = Helpers.randInt(1, 7);
        die2 = Helpers.randInt(1, 7);
        GameLog.instance.logEvent(GameManager.instance.getCurrentPlayer() + " rolled a " + getDiceTotal());
        GameManager.instance.distributeResources();

        if (getDiceTotal() == 7) {
            //before this, players with resource counts above 7 choose half their resources to lose, someone with 9 will choose 4
            ItemPlacementController.placeRobber();
        }
    }

    public int getDie1() {
        return die1;
    }

    public int getDie2() {
        return die2;
    }

    public int getDiceTotal() {
        return die1 + die2;
    }
}
