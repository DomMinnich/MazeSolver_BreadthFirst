import java.util.Scanner;

public class Maze {
    private Cell[][] maze;
    Cell start;
    Cell finish;
    int rows;
    int cols;
    int mazeSelected = MazeGUI.mazeSelected;

    public Maze(Cell[][] maze2) {
        maze = maze2;
    }

    public Maze(Scanner input) {
        // get mazeCount from MazeGUI class
        if (mazeSelected == 1) {
            rows = input.nextInt();
            cols = input.nextInt();
            input.nextLine();
            input.nextLine();
        } else {
            for (int i = 0; i < mazeSelected - 1; i++) {
                input.nextLine();
                input.nextLine();
                while (!input.hasNextInt()) {
                    input.nextLine();
                }
            }
            rows = input.nextInt();
            cols = input.nextInt();
            input.nextLine();
            input.nextLine();
        }
        Cell[][] maze2 = new Cell[rows][cols];
        Maze temp = new Maze(maze2);
        for (int i = 0; i < rows; i++) {
            String line1 = input.nextLine();
            for (int j = 0; j < cols; j++) {
                Location loc = new Location(i, j);
                Boolean eastWall = line1.charAt(j * 2 + 2) == '|';
                Boolean southWall = line1.charAt(j * 2 + 1) == '_';
                temp.setCell(loc, eastWall, southWall);
            }
        }
        maze = temp.maze;
    }

    public Cell getCellAtLocation(Location location) {
        return maze[location.getRow()][location.getCol()];
    }

    public Cell[] getCellsAtLocations(Location[] locations) {
        Cell[] cells = new Cell[locations.length];
        for (int i = 0; i < locations.length; i++) {
            cells[i] = getCellAtLocation(locations[i]);
        }
        return cells;
    }

    public String toString() {
        return toString(new SetArr<Cell>());
    }

    public String toString(SetArr<Cell> pathTaken) {
        String mazeString = "   ";
        for (int i = 0; i < maze[0].length - 1; i++) {
            mazeString += "_ ";
        }
        mazeString += "\n";

        for (int i = 0; i < maze.length; i++) {
            mazeString += "|";
            for (int j = 0; j < maze[i].length; j++) {
                if (pathTaken.contains(maze[i][j])) {
                    mazeString += maze[i][j].toString("▫");
                } else {
                    mazeString += maze[i][j].toString();
                }
            }
            mazeString += "\n";
        }
        return mazeString;
    }

    public void setCell(Location location, Cell north, Cell west, boolean eastWall, boolean southWall) {
        maze[location.getRow()][location.getCol()] = new Cell(location, north, west, eastWall, southWall);
        // System.out.println("within setCell");
        if (location.getRow() < maze.length) {
            maze[location.getRow() + 1][location.getCol()].setNorthNeighbor(maze[location.getRow()][location.getCol()]);
            // System.out.println("Attempted first");
        }
        if (location.getCol() < maze[0].length) {
            maze[location.getRow()][location.getCol() + 1].setWestNeighbor(maze[location.getRow()][location.getCol()]);
            // System.out.println("Attempted");
        }
    }

    public void setCell(Location location, boolean eastWall, boolean southWall) {
        Cell cell = getCellAtLocation(location);
        Cell north = null;
        Cell west = null;
        if (location.getRow() > 0) {
            north = getCellAtLocation(new Location(location.getRow() - 1, location.getCol()));
        }
        if (location.getCol() > 0) {
            west = getCellAtLocation(new Location(location.getRow(), location.getCol() - 1));
        }
        maze[location.getRow()][location.getCol()] = new Cell(location, north, west, eastWall, southWall);
    }

    public int getRows() {
        return maze.length;
    }

    public int getCols() {
        return maze[0].length;
    }

    public Cell getStart() {
        return getCellAtLocation(new Location(0, 0));
    }

    public Cell getFinish() {
        return getCellAtLocation(new Location(getRows() - 1, getCols() - 1));
    }

    public void setMaze(Maze maze2) {
        maze = maze2.maze;
    }

    public Maze getMaze() {
        return this;
    }

}
