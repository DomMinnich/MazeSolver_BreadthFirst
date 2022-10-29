public abstract class MazeRunner {
    Maze2 maze;
    SetArr<Cell2> pathTaken;
    protected Cell2 start;
    protected Cell2 finish;

    public MazeRunner(Cell2[][] maze, Cell2 start, Cell2 finish) {
        this.maze = new Maze2(maze);
        start = this.start;
        finish = this.finish;
        pathTaken = new SetArr<Cell2>();

    
    }

    abstract boolean runMaze();

}
