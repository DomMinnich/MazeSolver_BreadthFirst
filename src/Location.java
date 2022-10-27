//Updated 10/27/2022 5:37pm R
public class Location {
    private int row;
    private int column;
    private Location previous;

    public Location(int row, int column, Location previous) {
        this.row = row;
        this.column = column;
        previous = null;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Location previous() {
        return previous;
    }

    public void setPrevious(Location previous) {
        this.previous = previous;
    }

    public boolean equals(Object other) {
        if (other instanceof Location) {
            Location otherLocation = (Location) other;
            return otherLocation.row == row && otherLocation.column == column;
        }
        return false;
    }
}
