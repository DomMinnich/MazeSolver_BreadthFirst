//public static method final int NORTH = 0;
//public static method final int SOUTH = 1;
//public static method final int EAST = 2;
//public static method final int WEST = 3;
//private boolean neighbors[] = new boolean[4] The array variable represents the four possible connections to other cells within a maze. For example, neighbors[EAST] should be true if the maze allows travel from the current Cell to the next Cell to the right.
//private boolean onPath  When a path through a maze has been determined, this variable represents either the Cell is on the path or not.

public class Cell {
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    private boolean neighbors[] = new boolean[4];
    private boolean onPath;

    public Cell() {
        for (int i = 0; i < 4; i++) {
            neighbors[i] = false;
        }
        onPath = false;
    }

    public void setNeighbor(int direction) {
        neighbors[direction] = true;
    }

    //getNeighbor() method
    public Cell getNeighbor(int direction) {
        if (neighbors[direction]) {
            return new Cell();
        } else {
            return null;
        }
    }

    public boolean hasNeighbor(int direction) {
        return neighbors[direction];
    }

    public void setOnPath() {
        onPath = true;
    }

    public boolean isOnPath() {
        return onPath;
    }

    //set the cell char
    public void setCellChar(char c) {
        if (c == '|') {
            neighbors[Cell.NORTH] = true;
        } else if (c == '_') {
            neighbors[Cell.WEST] = true;
            neighbors[Cell.EAST] = true;
        } else if (c == ' ') {
            neighbors[Cell.NORTH] = true;
            neighbors[Cell.SOUTH] = true;
            neighbors[Cell.WEST] = true;
            neighbors[Cell.EAST] = true;
        }
    }

    //get the cell char
    //first check if it is 'start' or 'finish'
    //then check if it is on the path
    //then check if it is a corner
    //then check if it is a vertical line
    //then check if it is a horizontal line
    //then it is a space
    


    public char getCellChar() {
        if (neighbors[Cell.NORTH] && neighbors[Cell.SOUTH] && neighbors[Cell.WEST] && neighbors[Cell.EAST]) {
            return ' ';
        } else if (neighbors[Cell.NORTH] && neighbors[Cell.SOUTH] && neighbors[Cell.WEST]) {
            return '_';
        } else if (neighbors[Cell.NORTH] && neighbors[Cell.SOUTH] && neighbors[Cell.EAST]) {
            return '_';
        } else if (neighbors[Cell.NORTH] && neighbors[Cell.SOUTH]) {
            return '|';
        } else if (neighbors[Cell.WEST] && neighbors[Cell.EAST]) {
            return '_';
        } else if (neighbors[Cell.NORTH] && neighbors[Cell.WEST]) {
            return '_';
        } else if (neighbors[Cell.NORTH] && neighbors[Cell.EAST]) {
            return '_';
        } else if (neighbors[Cell.SOUTH] && neighbors[Cell.WEST]) {
            return '_';
        } else if (neighbors[Cell.SOUTH] && neighbors[Cell.EAST]) {
            return '_';
        } else if (neighbors[Cell.NORTH]) {
            return '|';
        } else if (neighbors[Cell.SOUTH]) {
            return '|';
        } else if (neighbors[Cell.WEST]) {
            return '_';
        } else if (neighbors[Cell.EAST]) {
            return '_';
        } else {
            return 'X';
        }
    }


    
   
    
}
