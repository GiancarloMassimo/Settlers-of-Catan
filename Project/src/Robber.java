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
//    public ArrayList<Player> check(){
//        checks all 6 nodes to see if there is a building, return an array of players
//    }
}
