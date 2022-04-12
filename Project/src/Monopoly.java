//public class Monopoly extends ProgressCard{
//
//    public Monopoly(Player o){
//        super(o,ProgressCardType.Monopoly);
//    }
//    //get one requested card from all players, once finished returns the updated player arraylist to update all card counts
//    public Player[] use(String req, Player[] playerList){
//        Player[] pl = playerList;
//        int cnt=0;
//        Inventory currentPlayerInventory;
//        Player owner = super.getOwner();
//        //subtracts 1 from the number of that players resource excluding the owner/user, then counts the amount the owner gets
//        for (int i =0;i< playerList.length;i++) {
//            currentPlayerInventory=playerList[i].getPlayerInventory();
//            if (playerList[i] != owner) {
//                if (currentPlayerInventory.getResourceCnt(req) != 0)
//                    currentPlayerInventory.changeResource(req,-1);
//                cnt++;
//            }
//        }
//
//
//        return pl;
//    }
//}
