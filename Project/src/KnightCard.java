import java.util.ArrayList;

public class KnightCard extends DevelopmentCard{

    public KnightCard(Player o) {
        super(o);
    }
    public ArrayList<Player> use(int c, int r, Robber rob, Tile[][] map){
        return rob.move(c,r,map);
    }
}
