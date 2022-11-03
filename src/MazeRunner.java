public abstract class MazeRunner {
    protected Maze2 maze;
    protected SetArr<Cell2> pathTaken;
    protected Cell2 start;
    protected Cell2 finish;

    public MazeRunner(Cell2[][] maze, Cell2 start, Cell2 finish) {
        this.maze = new Maze2(maze);
        start = this.start;
        finish = this.finish;
        pathTaken = new SetArr<Cell2>();

    }

    public MazeRunner(Maze2 maze2, Cell2 start, Cell2 finish) {
        this.maze = maze2;
        start = this.start;
        finish = this.finish;
        pathTaken = new SetArr<Cell2>();
    }

    abstract boolean runMaze();

    public String mazeToString(){
        return maze.toString();
    }

}
