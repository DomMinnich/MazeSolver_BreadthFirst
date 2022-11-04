public abstract class MazeRunner {
    protected Maze maze;
    protected SetArr<Cell> pathTaken;
    protected Cell start;
    protected Cell finish;

    abstract boolean runMaze();

    public MazeRunner(Cell[][] maze, Cell start, Cell finish) {
        this.maze = new Maze(maze);
        start = this.start;
        finish = this.finish;
        pathTaken = new SetArr<Cell>();
    }

    public MazeRunner(Maze maze2, Cell start, Cell finish) {
        this.maze = maze2;
        start = this.start;
        finish = this.finish;
        pathTaken = new SetArr<Cell>();
    }

    public String mazeToString() {
        return maze.toString();
    }

}
