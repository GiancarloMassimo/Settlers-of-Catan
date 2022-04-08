public class Dice {
    private int die1 = 1, die2 = 1;

    public void rollDice() {
        die1 = Helpers.RandInt(1, 7);
        die2 = Helpers.RandInt(1, 7);
        GameLog.instance.logEvent(GameManager.instance.getCurrentPlayer() + " rolled a " + getDiceTotal());
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
