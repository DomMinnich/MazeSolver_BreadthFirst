public class Cell2 {// 1

    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    private boolean neighbors[] = new boolean[4];
    boolean isWallE = false;
    boolean isWallS = false;
    boolean isWallW = false;
    boolean isWallN = false;
    Location location;
    int horizontalLocation = 0;
    int verticalLocation = 0;
    Cell2 northNeighbor = null;
    Cell2 westNeighbor = null;

    public boolean hasWallNorth() {
        if (this.northNeighbor == null) {
            return true;
        }
        return northNeighbor.hasWallSouth();
    }

    public boolean hasWallSouth() {
        return isWallS;

    }

    public boolean hasWallEast() {
        return isWallE;

    }

    public boolean hasWallWest() {
        if (this.westNeighbor == null) {
            return true;
        }
        return westNeighbor.hasWallEast();

    }

    public Cell2(Location location, Cell2 northNeighbor, Cell2 westNeighbor, boolean isWallE, boolean isWallS) {
        this.location = location;
        this.northNeighbor = northNeighbor;
        this.westNeighbor = westNeighbor;
        this.isWallE = isWallE;
        this.isWallS = isWallS;
    }

    public Location[] getAccessibleNeighborLocations() {
        Location[] neighborLocations = new Location[getNeighborsNum()];
        int currentNeightborNum = 0;

        if (!hasWallNorth()) {
            neighborLocations[currentNeightborNum] = location.getLocation(NORTH);
            currentNeightborNum++;
        }
        if (!hasWallSouth()) {
            neighborLocations[currentNeightborNum] = location.getLocation(SOUTH);
            currentNeightborNum++;
        }
        if (!hasWallEast()) {
            neighborLocations[currentNeightborNum] = location.getLocation(EAST);
            currentNeightborNum++;
        }
        if (!hasWallWest()) {
            neighborLocations[currentNeightborNum] = location.getLocation(WEST);
            currentNeightborNum++;
        }
        return neighborLocations;

    }

    public int getNeighborsNum() {
        int neighborsNum = 0;
        if (!hasWallNorth()) {
            neighborsNum++;
        }
        if (!hasWallSouth()) {
            neighborsNum++;
        }
        if (!hasWallEast()) {
            neighborsNum++;
        }
        if (!hasWallWest()) {
            neighborsNum++;
        }
        return neighborsNum;
    }

    public void setNorthNeighbor(Cell2 northNeighbor) {
        this.northNeighbor = northNeighbor;
    }

    public Cell2 getNorthNeighbor() {
        return northNeighbor;
    }

    public void setWestNeighbor(Cell2 westNeighbor) {
        this.westNeighbor = westNeighbor;
    }

    public Cell2 getWestNeighbor() {
        return westNeighbor;
    }

    public String toStringEast() {
        if (hasWallEast()) {
            return "|";
        } else {
            return " ";
        }
    }

    public String toStringSouth() {
        if (hasWallSouth()) {
            return "_";
        } else {
            return " ";
        }
    }

    public String toString() {
        return toStringSouth() + toStringEast();
    }

    public String toString(String substitute) {

        return substitute + toStringEast();

    }

    // public String toStringPath(){
    // return toStringSouth()+toStringSouthPath();
    // }

    // private String toStringSouthPath() {
    // return "X";
    // }

    public boolean equals(Cell2 otherCell) {
        return location.equals(otherCell.location);
    }

}
