public class ProgressCard extends DevelopmentCard{

    private ProgressCardType type;

    public ProgressCard(Player o, ProgressCardType t){
        super(o);
        type = t;
    }

    public Player getOwner(){
        return super.getOwner();
    }

    public ProgressCardType getProgressCardType(){
        return type;
    }

}
