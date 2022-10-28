//Updated 10/27/2022 5:37pm R
//int row and column are the tow and column numbers of a Cell
//public method setPrevious(Location previous) sets the pointer back to the previous Location
//public method previous() Returns a pointer to the previous Location
//public method getLocation(int direction) This method returns a Location object with the coordinates of an adjacent cell in the specified direction
//public method equals(Object other) This method returns true if the two Locations are equal, false otherwise

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
