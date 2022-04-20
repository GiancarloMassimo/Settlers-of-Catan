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

    public Player trade(ResourceType req, Player p, ResourceType lose){//lose is what they're giving away
        Player mod=p;
       if (requirement(mod, lose)){
           if (type==PortType.Generic)
               mod.getInventory().payItem(lose,quantity);
           else
               mod.getInventory().payItem(give,quantity);
           mod.getInventory().receiveItem(req, 1);
       }
       return mod;
    }

    public boolean requirement(Player p, ResourceType lose){//checks if players can trade
        if (type==PortType.Generic)
            if (p.getInventory().getResourceCount(lose) >= quantity)
                return true;
            else
                return p.getInventory().getResourceCount(give) >= quantity;
        return false;
    }
}
