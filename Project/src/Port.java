public class Port {
    PortType type;
    ResourceType give; //what is wants, only used when type is special
    int quantity;//how much it needs
    public Port(ResourceType resource, PortType type){
        this.type = type;
        if(type==PortType.Generic){
            quantity = 3;
        }
        else {
            quantity = 2;
            give = resource;
        }
    }
//    public boolean requirement(){
//        checks if players can trade
//    }
//    public void trade(){
//        removes cards and gives to player
//    }
}
