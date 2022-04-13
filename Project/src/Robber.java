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
    public void SetRow(int r){
         row = r;
    }public int getCol(){
        return col;
    }public void SetCol(int c){
        col = c;
    }
    //checks all 6 nodes(edges) to see if there is a building, return an array of players\
    public ArrayList<Player> check(){
        ArrayList<Player> players = new ArrayList<>();
        return players;
    }
}
