import java.util.ArrayList;
import java.util.Collections;

public class TileMapFactory {
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

    private final ArrayList<TileType> tileStack = new ArrayList<>();

    public TileMapFactory() {
        shuffleTileStack();
        fillTileMatrix();
        do {
            System.out.println();
            clearMapNumbers();
            fillTileNumbers();
        } while (areRedNumbersTouching());
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
                new int[] {0, -1},
                new int[] {1, 0},
                new int[] {0, 1},
                new int[] {-1, 0},
        };

        int r, c;
        int index = 0;
        int counterclockwiseCycleIndex = 0;

        // Pick a starting point on the outer edge of the grid
        r = Helpers.RandInt(0, map.length);

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
        }

        // save starting position for inner loop
        int sr  = r;
        int sc = c;

        // Outer loop
        int count = 0;
        while (count < 12) {
            count++;
            /*System.out.println((counterclockwiseCycleIndex == 0 ? "down" : counterclockwiseCycleIndex == 1 ? "right"
                    : counterclockwiseCycleIndex == 2 ? "up" : "left") + ": " + r + " " + c);*/
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

            r = tempR;
            c = tempC;
        }

        // Adjust position for inner loop
        /*
        Starting points for inner loop
        0, 0 -> 1, 1
        1, 0 -> 2, 1
        2, 0 -> 2, 1
        3, 0 -> 2, 1
        4, 0 -> 3, 1
        4, 1 -> 3, 2
        4, 2 -> 3, 2
        2, 4 -> 2, 3
        0, 2 -> 1, 2
        0, 1 -> 1, 2

        Special cases: Add to 4 and not [4, 0]
        3, 3 -> 2, 3
        1, 3 -> 1, 2
        */

        r = sr; c = sc;

        if (r == 3 && c == 3) {
            r = 2;
        } else if (r == 1 && c == 3) {
            c = 2;
        } else {
            if (sr > 2) {
                r--;
            } else if (sr < 2) {
                r++;
            }

            if (sc < 2) {
                c++;
            } else if (sc > 2) {
                c--;
            }
        }

        System.out.println();
        //Inner loop
        while (count < 18) {
            count++;
            /*System.out.println((counterclockwiseCycleIndex == 0 ? "down" : counterclockwiseCycleIndex == 1 ? "right"
                    : counterclockwiseCycleIndex == 2 ? "up" : "left") + ": " + r + " " + c);*/
            if (map[r][c].getType() != TileType.Desert) {
                map[r][c].setNum(NUMBER_STACK[index]);
                index++;
            } else {
                map[r][c].setNum(0);
            }

            int tempR = r + directionCycle[counterclockwiseCycleIndex][0];
            int tempC = c + directionCycle[counterclockwiseCycleIndex][1];

            // Special case for [2, 3] because it's being an absolute butt
            if (r == 2 && c == 3) {
                tempR = 1; tempC = 2;
                counterclockwiseCycleIndex = 0;
            } else {
                if (map[tempR][tempC].getNum() != -1) {
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
            }

            r = tempR;
            c = tempC;
        }

        // Center
        if (map[2][2].getType() != TileType.Desert)
            map[2][2].setNum(NUMBER_STACK[NUMBER_STACK.length - 1]);
    }

    private boolean areRedNumbersTouching() {
        int[][] hexDirections = {
                new int[]{0, 1},
                new int[]{0, -1},
                new int[]{1, 0},
                new int[]{-1, 0},
                new int[]{1, -1},
                new int[]{-1, -1}
        };

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                int n = map[r][c].getNum();
                if (n == 6 || n == 8) {
                    for (int i = 0; i < hexDirections.length; i++) {
                        try {
                            int oN = map[r + hexDirections[i][0]][c + hexDirections[i][1]].getNum();
                            if (oN == 6 || oN == 8) {
                                System.out.println("touching");
                                return true;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            continue;
                        }
                    }
                }
            }
        }

        return false;
    }

    private void clearMapNumbers() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                map[r][c].setNum(-1);
            }
        }
    }

    public Tile[][] getMap() {
        return map;
    }
}
