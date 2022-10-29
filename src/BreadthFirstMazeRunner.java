import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BreadthFirstMazeRunner extends MazeRunner {
    QueueArr<Cell2> enqueuedCells = new QueueArr<Cell2>();
    SetArr<Cell2> visitedCells = new SetArr<Cell2>();

    // If path found set pathTaken to true else false
    public boolean runMaze() {
        System.out.println("add the start cell to the queue");
        enqueuedCells.enqueue(start);
        // while the queue is not empty
        System.out.println("while the queue is not empty");
        while (!enqueuedCells.isEmpty()) {
            // dequeue the cell

            System.out.println("dequeue the cell");
            Cell2 current = enqueuedCells.dequeue();

            visitedCells.enter(current);
            if (current == finish) {
                System.out.println("this is the finish");
                System.out.println("return true");
                return true;

            }

            Cell2 neighbors[] = maze.getCellsAtLocations(current.getNeighborLocations());

            // for each neighbor of the cell
            for (Cell2 cell2 : neighbors) {
                enqueuedCells.enqueue(cell2);

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
        return false;

    }

    public BreadthFirstMazeRunner(Cell2[][] maze, Cell2 start, Cell2 finish) {
        super(maze, start, finish);
    }

    public static void main(String[] args) throws FileNotFoundException {
        // get file from user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the file containing the maze: ");
        String fileName = input.nextLine();
        File file = new File(fileName);
        Scanner fileInput = new Scanner(file);
        Maze2 maze = new Maze2(fileInput);
        
        System.out.println(maze.toStringM());

}
}
