public class VictoryPointCard extends DevelopmentCard{
    private String title; // all points are 1
    public VictoryPointCard(Player o){
        super(o);
        //title =t;
    }

    public void use(){
        getOwner().addSecretVictoryPoints(1);
    }
}
