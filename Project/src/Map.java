import java.util.ArrayList;
import java.util.Collections;

public class Map {
    private ArrayList<TileType> tileStack = new ArrayList<>();
    private Tile[][] map = {
            new Tile[3],
            new Tile[4],
            new Tile[5],
            new Tile[4],
            new Tile[3]
    };

    private final int[] NUMBER_STACK = {
        5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11
    };

    public Map() {
        shuffleTileStack();
        fillTileMatrix();
        fillTileNumbers();
    }

    private void shuffleTileStack() {
        for (int i = 0; i < 4; i++) {
            tileStack.add(TileType.Sheep);
        }
        for (int i = 0; i < 4; i++) {
            tileStack.add(TileType.Wood);
        }
        for (int i = 0; i < 4; i++) {
            tileStack.add(TileType.Wheat);
        }
        for (int i = 0; i < 3; i++) {
            tileStack.add(TileType.Ore);
        }
        for (int i = 0; i < 3; i++) {
            tileStack.add(TileType.Brick);
        }
        tileStack.add(TileType.Desert);

        Collections.shuffle(tileStack);
    }

    private void fillTileMatrix() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                map[r][c] = new Tile(tileStack.remove(0), r, c);
            }
        }
    }

    // NOTE: PIT OF DESPAIR -- CANCER ALGORITHM AHEAD
    // HAVE BLEACH PREPARED FOR YOUR EYES IF YOU ENTER
    private void fillTileNumbers() {
        int[][] directionCycle = {
                new int[] {1, 0, 4},
                new int[] {0, 1, 2},
                new int[] {-1, 0, 4},
                new int[] {0, -1, 2}
        };

        int r, c;
        int index = 0;
        int counterclockwiseCycleIndex = 0;

        // Pick a starting point on the outer edge of the grid
        /*r = Helpers.RandInt(0, map.length);

        if (r == 0 || r == map.length - 1) {
            c = Helpers.RandInt(0, map[r].length);
            if (r == 0) {
                counterclockwiseCycleIndex = 0;
            } else {
                counterclockwiseCycleIndex = 2;
            }
        } else {
            c = Helpers.RandInt(0, 2) == 0 ? 0 : map[r].length - 1;
            if (c == 0) {
                counterclockwiseCycleIndex = 1;
            } else {
                counterclockwiseCycleIndex = 3;
            }
        }*/
       //pick a random starting corner
        int[][] startingPoints = {
                new int[]{0, 0},
                new int[]{4, 0},
                new int[]{4, 2},
                new int[]{0, 2}
        };
        int startingIndex = Helpers.RandInt(0, startingPoints.length);
        int[] startingPoint = startingPoints[startingIndex];
        r = startingPoint[0]; c = startingPoint[1];
        counterclockwiseCycleIndex = startingIndex;

        int count = 0;
        directionCycle[(counterclockwiseCycleIndex + 3) % 4][2]--;
        boolean first = true;
        while (index < NUMBER_STACK.length) {
            // System.out.println((counterclockwiseCycleIndex == 0 ? "down" : counterclockwiseCycleIndex == 1 ? "right"
            // : counterclockwiseCycleIndex == 2 ? "up" : "left") + ": " + r + " " + c + " - " + count);
            if (map[r][c].getType() != TileType.Desert) {
                map[r][c].setNum(NUMBER_STACK[index]);
                index++;
            } else {
                map[r][c].setNum(0);
            }

            int tempR = r + directionCycle[counterclockwiseCycleIndex][0];
            int tempC = c + directionCycle[counterclockwiseCycleIndex][1];

            if (tempR == -1 || tempR == map.length
                    || tempC == -1 || tempC == map[r].length) {
                counterclockwiseCycleIndex++;
                counterclockwiseCycleIndex %= directionCycle.length;
                tempR = r + directionCycle[counterclockwiseCycleIndex][0];
                tempC = c + directionCycle[counterclockwiseCycleIndex][1];
            }
            if (tempR < r) {
                if (r > 2) {
                    tempC++;
                } else {
                    tempC--;
                }
            }


            if (map[tempR][tempC].getNum() != -1) {
                counterclockwiseCycleIndex++;
                counterclockwiseCycleIndex %= directionCycle.length;
                if (index == NUMBER_STACK.length - 1) {
                    r = 2; c = 2;
                }

                if (r == 0 && c == 1) {
                    r = 1;
                } else if (r == 3 && c == 0) {
                    c = 1;
                } else if (r == 4 && c == 1) {
                    r = 3;
                    c = 2;
                } else if (r == 1 && c == 3) {
                    c = 2;
                } else if (r == 1 && c == 1) {
                    r = 2;
                } else if (r == 3 && c == 1) {
                    c = 2;
                } else if (r == 3 && c == 2) {
                    c = 3;
                    r = 2;
                } else if (r == 1 && c == 2) {
                    c = 1;
                }
            } else {
                r = tempR;
                c = tempC;
            }
        }
    }

    public Tile[][] getMap() {
        return map;
    }
}
