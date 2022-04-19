import java.util.ArrayList;

public class Robber {
    private int row;
    private int col;
    private ArrayList<Player> robbed;//list of players who can be stolen from after the call of check
    public Robber(int r, int c){
        row=r;
        col=c;
    }

    public int getRow(){
        return row;
    }

    public int getCol() {
        return col;
    }

    public ArrayList<Player> move(int c, int r, Tile[][] map){
        row =r;
        col =c;
        robbed= check(map);
        return robbed;
    }
    //checks all 6 nodes(edges) to see if there is a building, return an array of players\
    public ArrayList<Player> check(Tile[][] map){
        ArrayList<Player> players = new ArrayList<>();
//        map[row][col]
        return players;
    }
}
