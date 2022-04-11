public class Port {
    PortType type;
    ResourceType give; //what is wants
    int quantity;//how much it needs
    public Port(ResourceType resource, PortType type){
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
