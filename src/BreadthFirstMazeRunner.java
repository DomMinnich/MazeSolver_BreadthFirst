import java.io.File;//1
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BreadthFirstMazeRunner extends MazeRunner {
    QueueArr<Cell2> enqueuedCells = new QueueArr<Cell2>();
    SetArr<Cell2> visitedCells = new SetArr<Cell2>();

    // If path found set pathTaken to true else false
    public boolean runMaze() {
        int count = 0;
        Cell2 start = maze.getStart();
        Cell2 finish = maze.getFinish();
        System.out.println("start" + start);
        System.out.println("finish" + finish);
        System.out.println("add the start cell to the queue");
        enqueuedCells.enqueue(start, new Location(0, 0));
        // while the queue is not empty
        System.out.println("while the queue is not empty");
        while (!enqueuedCells.isEmpty()) {
            count++;
            // dequeue the cell

            // System.out.println("dequeue the cell");
            Cell2 current = enqueuedCells.dequeue();

            visitedCells.enter(current);
            if (current == finish) {
                pathTaken = visitedCells;
                System.out.println(visitedCells + "this is the visited cells");
                System.out.println("count" + count);
                SetArr<Cell2> pathSolved = new SetArr<>();
                while (current != start) {
                    pathSolved.enter(current);
                    current = maze.getCellAtLocation(current.location.previous);

                }
                pathSolved.enter(current);
                pathTaken = pathSolved;
                return true;

            }
            Cell2 neighbors[] = maze.getCellsAtLocations(current.getAccessibleNeighborLocations());
            // for each neighbor of the cell
            for (Cell2 neighbor : neighbors) {
                if (!visitedCells.contains(neighbor) && !enqueuedCells.contains(neighbor)) {
                    enqueuedCells.enqueue(neighbor, current.location);

                }
            }
        }
        // TODO replace visited cells with 'X'

        // for (int i = 0; i < rows; i++) {
        // // System.out.println("replace visited cells with 'X Rows'");
        // for (int j = 0; j < cols; j++) {
        // // System.out.println("replace visited cells with 'X' Cols");
        // if (visited.contains(maze[i][j])) {
        // maze[i][j].setCellChar('X');
        // }
        // }
        // }
        // return false
        System.out.println("could not solve");
        System.out.println("count" + count);
        return false;

    }

    public BreadthFirstMazeRunner(Cell2[][] maze, Cell2 start, Cell2 finish) {
        super(maze, start, finish);
    }

    public BreadthFirstMazeRunner(Maze2 maze, Cell2 start, Cell2 finish) {
        super(maze, start, finish);
    }

    public static void main(String[] args) throws FileNotFoundException {
        // get file from user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the file containing the maze: ");
        String fileName = input.nextLine();
        File file = new File(fileName);
        Scanner fileInput = new Scanner(file);
        int num = fileInput.nextInt();
        System.out.println("Number = " + num);
        int num2 = fileInput.nextInt();
        System.out.println("Number = " + num2);
        int num3 = fileInput.nextInt();
        System.out.println("Number = " + num3);

        // Maze2 maze = new Maze2(fileInput);
        // System.out.println("printing maze");
        // System.out.println(maze.toString());

        // BreadthFirstMazeRunner runner = new BreadthFirstMazeRunner(maze,
        // maze.getStart(), maze.getFinish());
        // runner.runMaze();
        // System.out.println(runner.pathTaken.toString() + "setArr path");
        // System.out.println(maze.toString(runner.pathTaken));
    }

}
