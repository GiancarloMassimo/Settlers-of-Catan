public class RoadBuilding extends ProgressCard{

    public RoadBuilding(Player o){
        super(o,ProgressCardType.RoadBuilding);
    }

    public void use(){
        for (int i=0;i<2;i++)
            GameActionHandler.queueAction(
                    GameActionTypes.Instant,
                    () -> ItemPlacementController.placeRoad()
            );
    }

}
