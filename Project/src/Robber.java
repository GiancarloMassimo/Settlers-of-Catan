import java.util.ArrayList;

public class Robber {
    private int row;
    private int col;
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

    public void move(int c, int r){
        row =r;
        col =c;
        check();
    }
    //checks all 6 nodes(edges) to see if there is a building, return an array of players\
    public ArrayList<Player> check(){
        ArrayList<Player> players = new ArrayList<>();
        return players;
    }
}
