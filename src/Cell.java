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

    public boolean hasNeighbor(int direction) {
        return neighbors[direction];
    }

    public void setOnPath() {
        onPath = true;
    }

    public boolean isOnPath() {
        return onPath;
    }

    public static int opposite(int direction) {
        if (direction == NORTH) {
            return SOUTH;
        } else if (direction == SOUTH) {
            return NORTH;
        } else if (direction == EAST) {
            return WEST;
        } else if (direction == WEST) {
            return EAST;
        } else {
            return -1;
        }
    }
}
