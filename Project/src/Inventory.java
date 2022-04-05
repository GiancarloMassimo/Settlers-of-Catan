public class Inventory {
    private int numBrick, numSheep, numOre, numWheat, numWood;

    public Inventory(){
        numBrick=0;numSheep=0;numOre=0;numWheat=0;numWood=0;
    }

    //n can be negative or positive
    public void changeBrick(int n){
        numBrick+=n;
    }
    public void changeSheep(int n){
        numSheep+=n;
    }
    public void changeOre(int n){
        numOre+=n;
    }
    public void changeWheat(int n){
        numWheat+=n;
    }
    public void changeWood(int n){
        numWood+=n;
    }
    //needed must be positive
    public boolean available(int brickNeeded, int sheepNeeded, int oreNeeded, int wheatNeeded, int woodNeeded){//parameters must be positive
        return numBrick>=brickNeeded && numSheep>=sheepNeeded && numOre>=oreNeeded && numWheat>=wheatNeeded && numWood>=woodNeeded;
    }
    //subtracts resources and returns true if able to purchase, returns false if not able to purchase
    public boolean purchase(int brickNeeded, int sheepNeeded, int oreNeeded, int wheatNeeded, int woodNeeded){//parameters must be positive
        if(available(brickNeeded, sheepNeeded, oreNeeded, wheatNeeded, woodNeeded)){
            changeBrick(-1*brickNeeded);changeSheep(-1*sheepNeeded);changeOre(-1*oreNeeded);changeWheat(-1*wheatNeeded);changeWood(-1*woodNeeded);
            return true;
        }
        return false;
    }
}