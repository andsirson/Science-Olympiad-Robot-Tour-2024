public class MazeSolverQueue extends MazeSolver
{
    private MyQueue<Square> worklist;
    public MazeSolverQueue(Maze maze) {
            super(maze);
        }
    
        @Override
        void makeEmpty() {
            this.worklist = new MyQueue<>();
        }
    
        @Override
        boolean isEmpty() {
            return this.worklist.isEmpty();
        }
    
        @Override
        void add(Square square) {
            this.worklist.enqueue(square);
        }
    
        @Override
        Square next() {
            return this.worklist.dequeue();
        }
    
    }
