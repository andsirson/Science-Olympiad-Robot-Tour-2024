public class MazeSolverStack extends MazeSolver
{
    private MyStack<Square> stack;

    public MazeSolverStack(Maze maze)
    {
        super(maze);
    }
    
    /**
     * use optimized version (may not actually be faster) if optimize is true
     * @param maze which maze
     * @param optimize true if maze solver will use optimization, false if not
     */
    public MazeSolverStack(Maze maze, boolean optimize) 
    {
        super(maze);
        super.useOptimized = true;
    }

    /**
     * create an empty worklist 
    */
    public void makeEmpty()
    {
        stack = new MyStack<Square>();
    }
    /**
     * return true if the worklist is empty
     * @return true if the worklist is empty
     */
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    //add the given Square to the worklist
    public void add(Square sq)
    {
        stack.push(sq);
    }
    /**
     * return the "next" item from the worklist
     * @return the "next" item from the worklist
     */
    public Square next()
    {
        return stack.pop();
    }
}
