public class BreadthFirstMazeRunner extends MazeRunner {
    QueueArr<Cell> enqueuedCells = new QueueArr<Cell>();
    SetArr<Cell> visitedCells = new SetArr<Cell>();

    // If path found set pathTaken to true else false
    public boolean runMaze() {
        int count = 0;
        Cell start = maze.getStart();
        Cell finish = maze.getFinish();
        enqueuedCells.enqueue(start, new Location(0, 0));
        // while the queue is not empty
        while (!enqueuedCells.isEmpty()) {
            count++;
            // System.out.println("dequeue the cell");
            Cell current = enqueuedCells.dequeue();
            visitedCells.enter(current);
            if (current == finish) {
                pathTaken = visitedCells;
                SetArr<Cell> pathSolved = new SetArr<>();
                while (current != start) {
                    pathSolved.enter(current);
                    current = maze.getCellAtLocation(current.location.previous);
                }
                pathSolved.enter(current);
                pathTaken = pathSolved;
                return true;
            }
            Cell neighbors[] = maze.getCellsAtLocations(current.getAccessibleNeighborLocations());
            // for each neighbor of the cell
            for (Cell neighbor : neighbors) {
                if (!visitedCells.contains(neighbor) && !enqueuedCells.contains(neighbor)) {
                    enqueuedCells.enqueue(neighbor, current.location);
                }
            }
        }
        return false;
    }

    public BreadthFirstMazeRunner(Cell[][] maze, Cell start, Cell finish) {
        super(maze, start, finish);
    }

    public BreadthFirstMazeRunner(Maze maze, Cell start, Cell finish) {
        super(maze, start, finish);
    }
}
