public class Cell {
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    private boolean neighbors[] = new boolean[4];
    private boolean onPath;
    private char cellChar;

    public Cell() {
        for (int i = 0; i < 4; i++) {
            neighbors[i] = false;
        }
        cellChar = '|'; 
        onPath = false;
    }
    

    public void setNeighbor(int direction) {
        neighbors[direction] = true;
    }

    // getNeighbor() method
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

    // set the cell char
    public void setCellChar(char cellChar) {
        this.cellChar = cellChar;
    }

    // get the cell char
    public char getCellChar() {
        return cellChar;
    }

}
