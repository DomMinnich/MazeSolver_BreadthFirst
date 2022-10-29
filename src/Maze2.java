import java.util.Scanner;

public class Maze2 {
    private Cell2[][] maze;

    public Maze2(Cell2[][] maze2) {
        maze = maze2;
    }

    public Maze2(Scanner input) {
        int rows = input.nextInt();
        int cols = input.nextInt();
        input.nextLine();
        Cell2[][] maze2 = new Cell2[rows][cols];
         Maze2  temp =new Maze2(maze2);

      
        for (int i = 0; i < rows-3; i++) {
            String line1 = input.nextLine();
            String line2 = input.nextLine();
             System.out.println(line1 + "\n" + line2);
            for (int j = 0; j < cols; j++) {
                Location loc = new Location(i, j);

                Boolean southWall = line2.charAt(j * 2 + 2) == '_';
                Boolean eastWall = line1.charAt(j * 2 + 1) == '|';
                temp.setCell(loc, southWall, eastWall);
            }
        }
        maze =temp.maze;
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

    public String toStringM() {
        String mazeString = "   ";
        for (int i = 0; i < maze.length; i++) {
            mazeString += i + "__";
        }
        mazeString += "\n";

        for (int i = 0; i < maze.length; i++) {
            mazeString += "|";
            for (int j = 0; j < maze[i].length; j++) {
               // System.out.println("i: " + i + " j: " + j);
                System.out.println(mazeString);
                mazeString += " " + maze[i][j].toStringEast();
            }

            mazeString += "\n";
            for (int j = 0; j < maze[i].length; j++) {
                mazeString += maze[i][j].toStringSouth() + " ";
            }
            mazeString += "\n";
        }
        return mazeString;
    }

    public void setCell(Location location, Cell2 north, Cell2 west, boolean eastWall, boolean southWall) {
        maze[location.getRow()][location.getCol()] = new Cell2(location, north, west, eastWall, southWall);
        if (location.getRow() < maze.length) {
            maze[location.getRow() + 1][location.getCol()].setNorthNeighbor(maze[location.getRow()][location.getCol()]);
        }
        if (location.getCol() < maze[0].length) {
            maze[location.getRow()][location.getCol() + 1].setWestNeighbor(maze[location.getRow()][location.getCol()]);
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

}
