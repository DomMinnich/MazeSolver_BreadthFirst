import java.util.Scanner; //1

public class Maze2 {
    private Cell2[][] maze;
    Cell2 start;
    Cell2 finish;
    SetArr<Maze2> mazesArr = new SetArr<Maze2>();

    public Maze2(Cell2[][] maze2) {
        maze = maze2;
    }

    public Maze2(Scanner input) {
        int rows = input.nextInt();
        int cols = input.nextInt();
        input.nextLine();
        input.nextLine();
        Cell2[][] maze2 = new Cell2[rows][cols];
        Maze2 temp = new Maze2(maze2);
        for (int i = 0; i < rows; i++) {
            String line1 = input.nextLine();
            // System.out.println(line1 + " line1");
            for (int j = 0; j < cols; j++) {
                Location loc = new Location(i, j);
                Boolean eastWall = line1.charAt(j * 2 + 2) == '|';
                Boolean southWall = line1.charAt(j * 2 + 1) == '_';
                // System.out.println("tried to create cell");
                // System.out.println("eastWall: " + eastWall + "southWall: " + southWall +
                // "loc: " + loc);
                temp.setCell(loc, eastWall, southWall);
            }
        }
        maze = temp.maze;
        mazesArr.enter(temp);

    }

    public Cell2 getCellAtLocation(Location location) {
        return maze[location.getRow()][location.getCol()];
    }

    public Cell2[] getCellsAtLocations(Location[] locations) {
        Cell2[] cells = new Cell2[locations.length];
        for (int i = 0; i < locations.length; i++) {
            cells[i] = getCellAtLocation(locations[i]);
        }
        return cells;
    }

    public String toString() {
        return toString(new SetArr<Cell2>());

    }

    public String toString(SetArr<Cell2> pathTaken) {
        String mazeString = "   ";
        System.out.println(pathTaken.size() + " pathTaken.size()");
        for (int i = 0; i < maze[0].length - 1; i++) {
            mazeString += "_ ";
        }
        mazeString += "\n";

        for (int i = 0; i < maze.length; i++) {
            mazeString += "|";
            for (int j = 0; j < maze[i].length; j++) {
                // System.out.println("i: " + i + " j: " + j);
                // System.out.println(mazeString);
                if (pathTaken.contains(maze[i][j])) {
                    mazeString += maze[i][j].toString("x");

                } else {
                    mazeString += maze[i][j].toString();
                }
            }
            mazeString += "\n";
        }
        return mazeString;
    }

    public void setCell(Location location, Cell2 north, Cell2 west, boolean eastWall, boolean southWall) {
        maze[location.getRow()][location.getCol()] = new Cell2(location, north, west, eastWall, southWall);
        System.out.println("within setCell");
        if (location.getRow() < maze.length) {
            maze[location.getRow() + 1][location.getCol()].setNorthNeighbor(maze[location.getRow()][location.getCol()]);
            System.out.println("Attempted first");
        }
        if (location.getCol() < maze[0].length) {
            maze[location.getRow()][location.getCol() + 1].setWestNeighbor(maze[location.getRow()][location.getCol()]);
            System.out.println("Attempted");
        }

    }

    public void setCell(Location location, boolean eastWall, boolean southWall) {
        Cell2 cell = getCellAtLocation(location);
        Cell2 north = null;
        Cell2 west = null;
        if (location.getRow() > 0) {
            north = getCellAtLocation(new Location(location.getRow() - 1, location.getCol()));
        }
        if (location.getCol() > 0) {
            west = getCellAtLocation(new Location(location.getRow(), location.getCol() - 1));
        }
        maze[location.getRow()][location.getCol()] = new Cell2(location, north, west, eastWall, southWall);

    }

    public int getRows() {
        return maze.length;
    }

    public int getCols() {
        return maze[0].length;
    }

    public Cell2 getStart() {
        return getCellAtLocation(new Location(0, 0));
    }

    public Cell2 getFinish() {
        return getCellAtLocation(new Location(getRows() - 1, getCols() - 1));
    }

    // set Maze2 maze to Maze2 object
    public void setMaze(Maze2 maze2) {
        maze = maze2.maze;
    }

    // get Maze2 maze
    public Maze2 getMaze() {
        return this;
    }

}
