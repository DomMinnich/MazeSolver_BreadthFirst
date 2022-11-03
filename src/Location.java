public class Location {
    private int row;
    private int col;
    public Location previous;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setPrevious(Location previous) {
        this.previous = previous;
    }

    public Location getPrevious() {
        return previous;
    }

    public Location getLocation(int direction) {
        if (direction == Cell2.NORTH) {
            return new Location(row - 1, col);
        } else if (direction == Cell2.SOUTH) {
            return new Location(row + 1, col);
        } else if (direction == Cell2.EAST) {
            return new Location(row, col + 1);
        } else if (direction == Cell2.WEST) {
            return new Location(row, col - 1);
        } else {
            return null;
        }
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other instanceof Location) {
            Location otherLocation = (Location) other;
            return row == otherLocation.row && col == otherLocation.col;
        } else {
            return false;
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String toString() {
        return "(" + row + ", " + col + ")";
    }

}
