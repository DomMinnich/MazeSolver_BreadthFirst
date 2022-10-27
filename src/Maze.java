
//Updated 10/27/2022 5:37pm R
import java.util.Scanner;

/*
 * 1
 * public class Maze (Scanner input)
 * The constructor for the Maze class takes a Scanner as a parameter.
 * The scanner contains a representation of a maze
 * Internally the maze should be represented as a two-dimensional array of Cells
 * The class will store the cells visited using the SetArr class.
 * The class will store the cells to be visited using the QueueArr class.
 */

/*
 * 2
 * Method public asText()
 * Returns a text representatin of the maze without the first two lines of the
 * input file, the first two lines of the input file are the number of rows and
 * columns in the maze
 * if a path has been established, the asText() method should return a String
 * object showing the path in the maze using the character '@' to represent the
 * path
 * if no path exists, the text returned by asText() should be the same as the
 * input data
 */

/*
 * 3
 * Method public findPath()
 * the method determines if a path exists through the maze from the starting
 * location to the finishing location
 * if a path exists, the method records a path in the internal representation
 * the starting position is the very first cell in the maze
 * the finishing position is the very last cell in the maze
 * if no path exists, the text returned by asText() should be the same as the
 * input data
 */

/*
 * 4
 * Method public pathFound()
 * the method indicates whether a path has yet been found through a given maze
 */

public class Maze {
    private Cell[][] maze;
    private SetArr<Cell> visited;
    private QueueArr<Cell> toVisit;
    private int rows;
    private int cols;
    private boolean pathFound;

    public Maze(Scanner input) {
        rows = input.nextInt();
        cols = input.nextInt();
        maze = new Cell[rows][cols];
        visited = new SetArr<Cell>();
        toVisit = new QueueArr<Cell>();
        pathFound = false;
        input.nextLine();
        for (int i = 0; i < rows; i++) {
            String line = input.nextLine();
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new Cell(i, j, line.charAt(j));
            }
        }
    }

    public String asText() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j].isVisited()) {
                    result += "@";
                } else {
                    result += maze[i][j].getSymbol();
                }
            }
            result += " ";
        }
        return result;
    }

    public void findPath() {
        toVisit.enter(maze[0][0]);
        while (!toVisit.isEmpty()) {
            Cell current = toVisit.leave();
            if (current.isFinish()) {
                pathFound = true;
                return;
            }
            if (!current.isWall() && !visited.isElement(current)) {
                visited.enter(current);
                current.setVisited();
                toVisit.enter(maze[current.getRow() + 1][current.getCol()]);
                toVisit.enter(maze[current.getRow() - 1][current.getCol()]);
                toVisit.enter(maze[current.getRow()][current.getCol() + 1]);
                toVisit.enter(maze[current.getRow()][current.getCol() - 1]);
            }
        }
    }

    public boolean pathFound() {
        return pathFound;
    }
}
