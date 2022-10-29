public class Location {
    private int row;
    private int col;
    private Location previous;

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
        if (direction == Cell.NORTH) {
            return new Location(row - 1, col);
        } else if (direction == Cell.SOUTH) {
            return new Location(row + 1, col);
        } else if (direction == Cell.EAST) {
            return new Location(row, col + 1);
        } else if (direction == Cell.WEST) {
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
