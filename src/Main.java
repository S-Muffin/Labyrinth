import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    public static final char WALL = 'X';
    public static final char EDGE = 'R';

    static Cell[][] grid;

    static int yAmount = 20;
    static int xAmount = 20;
    static int diggerY;
    static int diggerX;
    static CopyOnWriteArrayList<XYCoordinate> dugCoordinates = new CopyOnWriteArrayList<>();
    static char sequence;
    static boolean exitFound = false;


    public static void main(String[] args) {
        int coordinatesArrayIndex = 0;

        grid = generateGrid();

        do {
            if (dugCoordinates.size() == 0) {
                diggerY = yAmount / 2;
                diggerX = xAmount / 2;
                buildLabyrinth();
            } else {
                for (Iterator<XYCoordinate> iterator = dugCoordinates.iterator(); iterator.hasNext(); ) {
                    XYCoordinate coordinate = iterator.next();
                    diggerY = coordinate.getY();
                    diggerX = coordinate.getX();
                    buildLabyrinth();
                    coordinatesArrayIndex++;
                }
            }
        } while (coordinatesArrayIndex < 20);

        printGrid();

        System.out.println(dugCoordinates);

    }

    private static void printGrid() {
        for (int i = 0; i < yAmount; i++) {
            for (int j = 0; j < xAmount; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
    }

    private static void digLeft() {
        diggerX--;
        if (grid[diggerY][diggerX].getContent() == EDGE) {
            exitFound = true;
        }
        dugCoordinates.add(grid[diggerY][diggerX].dig());
        grid[diggerY][diggerX].setContent((sequence));
        sequence++;

    }

    private static void digRight() {
        diggerX++;
        if (grid[diggerY][diggerX].getContent() == EDGE) {
            exitFound = true;
        }
        dugCoordinates.add(grid[diggerY][diggerX].dig());
        grid[diggerY][diggerX].setContent((sequence));
        sequence++;

    }

    private static void digUp() {
        diggerY--;
        if (grid[diggerY][diggerX].getContent() == EDGE) {
            exitFound = true;
        }
        dugCoordinates.add(grid[diggerY][diggerX].dig());
        grid[diggerY][diggerX].setContent((sequence));
        sequence++;
    }

    private static void digDown() {
        diggerY++;
        if (grid[diggerY][diggerX].getContent() == EDGE) {
            exitFound = true;
        }
        dugCoordinates.add(grid[diggerY][diggerX].dig());
        grid[diggerY][diggerX].setContent((sequence));
        sequence++;
    }

    private static int getRandomNumber() {
        return (int) (Math.random() * 4) + 1;
    }

    private static void buildLabyrinth() {

        int tries = 0;
        sequence = 'a';

        while (tries < 13) {

            int direction = getRandomNumber();

            try {
                switch (direction) {
                    case 1:
                        for (int i = 0; i < 2; i++) {
                            if (directionPossible(direction)) {
                                digDown();
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 2; i++) {
                            if (directionPossible(direction)) {
                                digLeft();
                            }
                        }

                        break;
                    case 3:
                        for (int i = 0; i < 2; i++) {
                            if (directionPossible(direction)) {
                                digUp();
                            }
                        }

                        break;
                    case 4:
                        for (int i = 0; i < 2; i++) {
                            if (directionPossible(direction)) {
                                digRight();
                            }
                        }

                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());

            }


            tries++;

        }

    }

    private static boolean directionPossible(int direction) {
        // down left up right
        /*
        int[] xv = {0, -1, 0, 1};
        int[] yv = {1, 0, -1, 0};

        int myDirection = direction - 1;

        int tempX = xv[myDirection];
        int tempY = yv[myDirection];

        Cell firstCell = grid[diggerX + tempX][diggerY + tempY];
        Cell secondCell = grid[diggerX + 2*tempX][diggerY + 2*tempY];
*/
        /*if (diggerY + 1 >= yAmount && diggerX + 1 >= xAmount && diggerY - 1 < 0 && diggerX - 1 < 0) {

            if (diggerY + 2 < yAmount && diggerX + 2 < xAmount && diggerY - 2 >= 0 && diggerX - 2 >= 0) {


            } else
                return false;
        }*/


        if (direction == 1 && diggerY + 1 < yAmount) {
            if (!exitFound && grid[diggerY + 1][diggerX].content == EDGE) {
                return true;
            } else if (diggerY + 2 < yAmount) {
                if (grid[diggerY + 1][diggerX].content == WALL && grid[diggerY + 2][diggerX].content == WALL) {
                    return true;
                }
            }

        } else if (direction == 2 && diggerX - 1 >= 0) {
            if (!exitFound && grid[diggerY][diggerX - 1].content == EDGE) {
                return true;
            } else if (diggerX - 2 >= 0) {
                if (grid[diggerY][diggerX - 1].content == WALL && grid[diggerY][diggerX - 2].content == WALL) {
                    return true;
                }
            }
        } else if (direction == 3 && diggerY - 1 >= 0) {
            if (!exitFound && grid[diggerY - 1][diggerX].content == EDGE) {
                return true;
            } else if (diggerY - 2 >= 0) {
                if (grid[diggerY - 1][diggerX].content == WALL && grid[diggerY - 2][diggerX].content == WALL) {
                    return true;
                }
            }

        } else if (direction == 4 && diggerX + 1 < xAmount) {
            if (!exitFound && grid[diggerY][diggerX + 1].content == EDGE) {
                return true;
            } else if (diggerX + 2 < xAmount) {
                if (grid[diggerY][diggerX + 1].content == WALL && grid[diggerY][diggerX + 2].content == WALL) {
                    return true;
                }
            }
        }


        return false;

    }


    static Cell[][] generateGrid() {


        Cell[][] grid = new Cell[yAmount][xAmount];

        for (int i = 0; i < yAmount; i++) {
            for (int j = 0; j < xAmount; j++) {
                grid[i][j] = new Cell(new XYCoordinate(i, j));

                if (i == 0 | j == 0 | i == yAmount - 1 | j == xAmount - 1) {
                    grid[i][j].content = EDGE;
                }
            }

        }

        return grid;

    }


}
