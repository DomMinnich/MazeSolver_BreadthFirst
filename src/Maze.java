import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 1)
 * public class Maze (Scanner input)
 * Ask the user for the name of the file containing the maze.
 * Read the maze from the file.
 * Internally the maze should be represented as a two-dimensional array of Cells
 */

   
/*
 * 2)
 * Method public solveMaze()
 * This method should solve the maze by finding a path from the start to the finish.
 * The method should return true if a path is found and false if no path is found.
 * The cells that have been visited should be stored in the Set<Cell> visited
 * The cells that have not yet been visited should be stored in the Queue<Cell> toVisit
 * The maze has '|' as walls and are not passable
 * The maze has '_' which can be passed through going west or east
 * The maze has ' ' which can be passed through going north or south or east or west
 */

/*
 * 3)
 * Method public hasPath()
 * This method returns true if a path exists, false otherwise based on the solve() method
 */

/*
 * 4)
 * public toString()
 * This method returns a string representation of the maze
 * The string representation of the maze should be the same as the input file
 * except that the path is marked with 'X'
 */

public class Maze {
    private Cell[][] maze;
    private SetArr<Cell> visited;
    private QueueArr<Cell> toVisit;
    private int rows;
    private int cols;
    private Cell start;
    private Cell finish;

    public Maze(Scanner input) {
        rows = input.nextInt();
        cols = input.nextInt();
        input.nextLine();
        maze = new Cell[rows][cols];
        visited = new SetArr<Cell>();
        toVisit = new QueueArr<Cell>();
        for (int i = 0; i < rows; i++) {
            String line = input.nextLine();
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new Cell();
                if (line.charAt(j) == '|') {
                    if (j > 0) {
                        maze[i][j].setNeighbor(Cell.WEST);
                        maze[i][j - 1].setNeighbor(Cell.EAST);
                    }
                    if (i > 0) {
                        maze[i][j].setNeighbor(Cell.NORTH);
                        maze[i - 1][j].setNeighbor(Cell.SOUTH);
                    }
                } else if (line.charAt(j) == '_') {
                    if (j > 0) {
                        maze[i][j].setNeighbor(Cell.WEST);
                        maze[i][j - 1].setNeighbor(Cell.EAST);
                    }
                } else if (line.charAt(j) == ' ') {
                    if (j > 0) {
                        maze[i][j].setNeighbor(Cell.WEST);
                        maze[i][j - 1].setNeighbor(Cell.EAST);
                    }
                    if (i > 0) {
                        maze[i][j].setNeighbor(Cell.NORTH);
                        maze[i - 1][j].setNeighbor(Cell.SOUTH);
                    }
                } else if (line.charAt(j) == 'S') {
                    start = maze[i][j];
                } else if (line.charAt(j) == 'F') {
                    finish = maze[i][j];
                }
            }
        }
    }


    public boolean solveMaze() {
        toVisit.enqueue(start);
        while (!toVisit.isEmpty()) {
            Cell current = toVisit.dequeue();
            if (current.equals(finish)) {
                return true;
            }
            visited.enter(current);
            if (current.hasNeighbor(Cell.NORTH) && !visited.contains(current.getNeighbor(Cell.NORTH))) {
                toVisit.enqueue(current.getNeighbor(Cell.NORTH));
            }
            if (current.hasNeighbor(Cell.SOUTH) && !visited.contains(current.getNeighbor(Cell.SOUTH))) {
                toVisit.enqueue(current.getNeighbor(Cell.SOUTH));
            }
            if (current.hasNeighbor(Cell.EAST) && !visited.contains(current.getNeighbor(Cell.EAST))) {
                toVisit.enqueue(current.getNeighbor(Cell.EAST));
            }
            if (current.hasNeighbor(Cell.WEST) && !visited.contains(current.getNeighbor(Cell.WEST))) {
                toVisit.enqueue(current.getNeighbor(Cell.WEST));
            }
        }
        return false;
    }







    public boolean hasPath() {
        return solveMaze();
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j].equals(start)) {
                    result += "S";
                } else if (maze[i][j].equals(finish)) {
                    result += "F";
                } else if (visited.contains(maze[i][j])) {
                    result += "X";
                } else {
                    result += maze[i][j].getSymbol();
                }
            }
            result += " ";
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the file containing the maze: ");
        String fileName = input.nextLine();
        try {
            Scanner file = new Scanner(new File(fileName));
            Maze maze = new Maze(file);
            System.out.println(maze);
            if (maze.hasPath()) {
                System.out.println("There is a path from the start to the finish.");
            } else {
                System.out.println("There is no path from the start to the finish.");
            }
            System.out.println(maze);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}





