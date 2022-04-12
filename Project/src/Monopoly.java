public class Monopoly extends ProgressCard{

    public Monopoly(Player o){
        super(o,ProgressCardType.Monopoly);
    }
    //get one requested card from all players, once finished returns the updated player arraylist to update all card counts
    public Player[] use(ResourceType req, Player[] playerList){
        Player[] list = playerList;
        int cnt=0;
        int pos =0;
        //gets location of owner
        for (int j =0;j<list.length;j++)
            if (list[j]==super.getOwner())
                pos=j;
        //subtracts 1 from the number of that players resource excluding the owner/user, then counts the amount the owner gets
        for (int i =0;i< list.length;i++)
            if (i != pos)
                if (list[i].getPlayerInventory().getResourceCount(req) != 0){
                    list[i].getPlayerInventory().payItem(req,1);
                cnt++;
                }

        list[pos].getPlayerInventory().receiveItem(req,cnt);

        return list;//returns an array of players to update their inventories
    }
}
