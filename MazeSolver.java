// At the start

// Create an (empty) worklist (stack/queue) of locations to explore.
// Add the start location to it.
// Each step thereafter

// Is the worklist empty? If so, the exit is unreachable; terminate the algorithm.
// Otherwise, grab the "next" location to explore from the worklist.
// Does the location correspond to the exit square? If so, the finish was reachable; terminate the algorithm and output the path you found.
// Otherwise, it is a reachable non-finish location that we haven't explored yet. So, explore it as follows:
// compute all the adjacent up, right, down, left locations that are inside the maze and aren't walls, and
// add them to the worklist for later exploration provided they have not previously been added to the worklist.
// Also, record the fact that you've explored this location so you won't ever have to explore it again. 
//Note that a location is considered "explored" once its neighbors have been put on the worklist. 
//The neighbors themselves are not "explored" until they are removed from the worklist and checked for their neighbors.

import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
//import org.hamcrest.core.Is;

public abstract class MazeSolver {
    protected Maze maze;
    protected Square finish;
    protected boolean isSolved;
    protected boolean useOptimized;

    /**
     * create an empty worklist 
    */
    abstract void makeEmpty();
    
    /**
     * return true if the worklist is empty
     * @return true if the worklist is empty
     */
    abstract boolean isEmpty();
    
    /**
     * //add the given Square to the worklist
     * @param sq add the given Square to the worklist
     */
    
    abstract void add(Square sq);
    
    /**
     * return the "next" item from the worklist
     * USES POP!!!
     * @return the "next" item from the worklist
     */
    abstract Square next();
    
    
    /**
    * Create a (non-abstract) constructor that takes a Maze as a parameter and stores it in a variable that the children classes can access.
    */
    MazeSolver(Maze newMaze)
    {
        maze = newMaze;
        finish = null;
        this.makeEmpty();
        this.add(maze.getStart());
        useOptimized = false;
    }
    
    /**
     * A non-abstract method that the application program can use to see if this algorithm has solved this maze. That is, has it determined the path to the exit or if there is no path.
        This method will return true if either:
        A path from the start to the exit has been found; OR
        You determine there is no such path (worklist is now empty)
    * @return true if A path from the start to the exit has been found; OR You determine there is no such path (worklist is now empty)
    */
    boolean isSolved()
    {
        if(this.isEmpty() || this.finish != null)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Returns either a string of the solution path as a list of coordinates [i,j] from the start to the exit or a message indicating no such path exists
     * If the maze isn't solved, you should probably return a message indicating such.
     * @return either a string of the solution path as a list of coordinates [i,j] from the start to the exit or a message indicating no such path exists
     */
    String getPath() 
    {
        if(!isSolved())
        {
            return "No Path Found: Maze Not Solved";
        }
        else if(this.finish != null) // finish is found
        {
            StringBuilder sb = new StringBuilder();
            Square previous = this.finish;
            while(previous.getType() != Square.START) // start ends at null
            {
                int x = previous.getCol(); // note: x and y are from the top left corner!
                int y = previous.getRow();
                sb.insert(0,","+"(" + x + "," + y + ")"); // insert at beginning each time
                previous = previous.getPrevious();
            }
            //previous == start
            int x = previous.getCol();
            int y = previous.getRow();
            sb.insert(0,"(" + x + "," + y + ")");
            
            String s = new String(sb);
            return s;
        }
        else // no path exists
        {
            return "No Path Found: No path exists";
        }
        
    }
    
    /**
     * perform one iteration of the algorithm above (i.e., steps 1 through 5) and return the Square that was just explored (and null if no such Square exists). 
     * Note that this is not an abstract method, that is, you should implement this method in the MazeSolver class by calling the abstract methods listed above.
     * In order to keep track of which squares have previously been added to the worklist, you will "mark" each square that you place in the worklist. 
     * Then, before you add a square to the worklist, you should first check that it is not marked (and if it is, refrain from adding it). 
     * Here is the suggestion for marking a Square: have each Square keep track of which Square added it to the worklist 
     * (i.e., "Which Square was being explored when this Square was added to the worklist?"). 
     * That is, add a new class member Square previous to the Square class, which will represent the Square previous to the current one; 
     * initialize this variable to null in the constructor/reset method. 
     * Then, when a Square is being added to the list for the first time, you will set the previous variable to point to the current Square (the Square that is being explored). 
     * If the previous variable is already non-null, then this Square has already been placed on the list, and you should not do so again. 
     * You may also want to add in additional methods to your Square class to help you with this.
     * @return the Square that was just explored (and null if no such Square exists)
     */
    Square step()
    {
        if(this.isEmpty())
        {
            return null;
        }
        // Is the worklist empty? If so, the exit is unreachable; terminate the algorithm.
        // Otherwise, grab the "next" location to explore from the worklist.

        Square nextSquare = this.next();
        // Does the location correspond to the exit square? If so, the finish was reachable; terminate the algorithm and output the path you found.

        if(nextSquare.getType()!= Square.START)
        {
            nextSquare.setType(Square.EXPLORED);
        }
        // Otherwise, it is a reachable non-finish location that we haven't explored yet. So, explore it as follows:
        // compute all the adjacent up, right, down, left locations that are inside the maze and aren't walls, and
        ArrayList<Square> neighbors = maze.getNeighbors(nextSquare);
        // add them to the worklist for later exploration provided they have not previously been added to the worklist.

        if(useOptimized) // extension only works for stack
        {
            // find just about which direction is best
            Square mazeFinish = maze.getFinish();
            int targetX = mazeFinish.getCol();
            int targetY = mazeFinish.getRow();

            Comparator<Square> myComparator = new Comparator<Square>() {
                public int compare(Square o1, Square o2) {
                    int distO1 = (int)Math.pow((o1.getCol()-targetX), 2) + (int)Math.pow((o1.getRow() - targetY), 2); // distance formula
                    int distO2 = (int)Math.pow((o2.getCol()-targetX), 2) + (int)Math.pow((o2.getRow() - targetY), 2);
                    if(distO1 == distO2)
                        return 0;
                    else if (distO1 < distO2)
                        return 1;
                    else
                        return -1;
                }
            };
            Collections.sort(neighbors,myComparator); // should always go for the ones that decrease distance first.
        }


        




        for(Square s : neighbors)
        {
            if(s.getPrevious() == null && s.getType() != Square.START)
            {
                s.setPrevious(nextSquare);
                if(s.setSpaceType(Square.ON_LIST))
                    this.add(s);
            }
            if(s.getType() == Square.EXIT) // on final path, returns path back.
                {
                    this.finish = s;
                    Square previous = s.getPrevious();
                    previous.setType(Square.ON_FINAL_PATH);
                    while(previous.getPrevious() != null && previous.getPrevious().getType() != Square.START)
                    {
                        previous = previous.getPrevious();
                        previous.setType(Square.ON_FINAL_PATH);
                        
                    }
                    isSolved = true;
                    return s;
            }  
        }

        return nextSquare;
    }

    
    /**
     * repeatedly call step() until you get to the exit square or the worklist is empty.
     */
    void solve()
    {
        while(!this.isSolved())
        {
            Square output = step();
            if(output.getType() == Square.EXIT)//done
            {
                break;
            }
        }
        
    }
}


