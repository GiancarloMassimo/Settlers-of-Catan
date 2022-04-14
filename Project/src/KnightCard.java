public class KnightCard extends DevelopmentCard{

    public KnightCard(Player o) {
        super(o);
    }
    public void use(int c, int r, Robber rob){ //dont know how to implement, maybe get the coordinates and change the robbers position
        rob.move(c,r);
    }
}
