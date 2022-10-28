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
 * Except that the path is marked with 'X'
 * Every row should be on a new line
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
                if (i==0 && j==1) {
                    start = maze[i][j];
                    start.setCellChar('S');
                } else if (i==rows-1 && j==cols-2) {
                    finish = maze[i][j];
                    finish.setCellChar('F');
                } 
                //set the second cell in the last row to the finish
                else if (i==rows && j==cols-1) {
                    maze[i][j] = finish;
                    //setchar to f
                    maze[i][j].setCellChar('F');
                    continue;
                }
               
                else if (line.charAt(j) == '|' || j ==cols-1) {
                    if (j > 0) {
                        maze[i][j].setNeighbor(Cell.WEST);
                        maze[i][j - 1].setNeighbor(Cell.EAST);
                        //set the cell char
                        maze[i][j].setCellChar(line.charAt(j));
                    }
                    if (i > 0) {
                        maze[i][j].setNeighbor(Cell.NORTH);
                        //set the cell char
                        maze[i][j].setCellChar(line.charAt(j));
                    }
                } else if (line.charAt(j) == '_') {
                    if (j > 0) {
                        maze[i][j].setNeighbor(Cell.WEST);
                        maze[i][j - 1].setNeighbor(Cell.EAST);
                        //set the cell char
                        maze[i][j].setCellChar(line.charAt(j));
                    }
                } else if (line.charAt(j) == ' ') {
                    if (j > 0) {
                        maze[i][j].setNeighbor(Cell.WEST);
                        maze[i][j - 1].setNeighbor(Cell.EAST);
                        //set the cell char
                        maze[i][j].setCellChar(line.charAt(j));
                    }
                    if (i > 0) {
                        maze[i][j].setNeighbor(Cell.NORTH);
                        maze[i - 1][j].setNeighbor(Cell.SOUTH);
                        //set the cell char
                        maze[i][j].setCellChar(line.charAt(j));
                    }
                }
            }
        }
    }

    //public solveMaze()
    //This method should solve the maze by finding a path from the start to the finish.
    //When the path from start to finish is found, the method should set the cellChar of each cell in the path to 'X'
    //The method should return true if a path is found and false if no path is found.
    //The cells that have been visited should be stored in the Set<Cell> visited
    //The cells that have not yet been visited should be stored in the Queue<Cell> toVisit
    // Use Location class to store the row and column of the cell
    //The maze has '|' as walls and are not passable
    //The maze has '_' which can be passed through going west or east
    //The maze has ' ' which can be passed through going north or south or east or west
    
    public boolean solveMaze2() {
        System.out.println("add the start cell to the queue");
        toVisit.enqueue(start);
        //while the queue is not empty
        System.out.println("while the queue is not empty");
        while (!toVisit.isEmpty()) {
            //dequeue the cell
            System.out.println("dequeue the cell");
            Cell current = toVisit.dequeue();
            //if the cell is the finish
            System.out.println("if the cell is the finish");
            if (current == finish) {
                //return true
                System.out.println("return true");
                return true;

            }
            //if the cell is not in the visited set
            System.out.println("if the cell is not in the visited set");
            if (!visited.contains(current)) {
                //add the cell to the visited set
                System.out.println("add the cell to the visited set");
                visited.enter(current);
                //for each neighbor of the cell
                System.out.println("for each neighbor of the cell");
                for (int i = 0; i < 4; i++) {
                    //if the neighbor is not null
                    System.out.println("if the neighbor is not null");
                    if (current.getNeighbor(i) != null) {
                        //if the neighbor is not in the visited set
                        System.out.println("if the neighbor is not in the visited set");
                        if (!visited.contains(current.getNeighbor(i))) {
                            //add the neighbor to the queue
                            System.out.println("add the neighbor to the queue");
                            toVisit.enqueue(current.getNeighbor(i));
                        }
                    }
                }
            }
        }
        //replace visited cells with 'X'
        System.out.println("replace visited cells with 'X'");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visited.contains(maze[i][j])) {
                    maze[i][j].setCellChar('X');
                }
            }
        }

        //return false
        return false;
    }


    public boolean solveMaze() {
        toVisit.enqueue(start);
        System.out.println("Step 1: Add the start cell to the queue");
        while (!toVisit.isEmpty()) {
            System.out.println("Step 2: Dequeue the cell");
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

    public String toStringA() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            result += "\n";
            for (int j = 0; j < cols; j++) {
                result += maze[i][j].getCellChar();
            }
            result += "";
        }
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        //get file from user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the file containing the maze: ");
        String fileName = input.nextLine();
        File file = new File(fileName);
        Scanner fileInput = new Scanner(file);
        Maze maze = new Maze(fileInput);
        System.out.println(maze.toStringA());
        System.out.println("The maze was solved : "+maze.solveMaze());
        System.out.println(maze.toStringA()+" Result after solve method");
    }
}





