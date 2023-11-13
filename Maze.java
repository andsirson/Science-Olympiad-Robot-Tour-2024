// public Maze()
// A constructor that takes no arguments
// NOTE: you can just omit writing a constructor and Java will just use the default values, the real work will be done with the loadMaze() method described next.
// boolean loadMaze(String fname)
// load the maze that is contained in the file named fname. The format of the file is described above. As a quick refresher, here is how you can declare a 2D array and fill it:

// private Square[][]  maze;   // somewhere as a class variable

// /* ... */

// this.maze = new Square[numRows][numCols];

// for (int row=0; row < numRows; row++) {
//     for (int col=0; col < numCols; col++) {
//         maze[row][col] = null;
//     }
// }
        
// If you encounter a problem while reading in the file, you should return false to indicate that it failed. Returning true indicates that you have now loaded the file from disk successfully.
// Be sure to catch the exception that is raised if the user specifies an incorrect file and print out an appropriate error message when this occurs and return false. Don't just let the program crash dumping the stack trace to the user.
// ArrayList<Square> getNeighbors(Square sq)
// return an ArrayList of the Square neighbors of the parameter Square sq. There will be at most four of these (to the North, East, South, and West) and you should list them in that order.
// If the square is on a border, skip over directions that are out of bounds of the maze. Don't be adding in null values.
// Square getStart()
// Square getFinish()
// Accessor methods that return the saved start/finish locations.
// void reset()
// Return the maze back to the initial state after loading. Erase any marking on squares (e.g., visited or worklist) but keep the layout.
// One way you might do this is by giving each Square a reset() method too, and then just loop through the squares and asking them to reset themselves.
// String toString
// return a String representation of this Maze in the format given below. (This is where it's useful to have a working Square.toString method.)
// For example, the maze above (i.e., maze-2) would be returned by toString as follows. (You may leave the spaces out from between squares if you so choose.)
//     _ _ _ _ _ _ # _ _ _ _ _ _               ______#______
//     # # _ # # # # # # # _ # _               ##_#######_#_
//     _ # _ _ _ _ _ _ _ _ _ # _               _#_________#_
//     _ # _ # # _ _ # # # _ # _      OR       _#_##__###_#_
//     _ _ _ # _ _ _ _ _ # _ # _               ___#_____#_#_
//     _ # # # # # _ # _ # _ # _               _#####_#_#_#_
//     _ _ _ _ S _ _ # _ _ # E _               ____S__#__#E_
// To keep things running quickly for larger mazes, you should use the StringBuilder class, which sort of works like an Arraylist, but for Strings. For example, you should replace the following O(n2) code

// String s = "";
// for( int i=0; i < data.length; i++ ) {
//     s = s + data[i];
// } 
// with the better O(n) code
// StringBuilder sb = new StringBuilder();
// for( int i=0; i < data.length; i++ ) {
//     sb.append(data[i]);
// }
// String s = new String(sb);
// See p584 of Weiss for reference, if you like.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class Maze { // https://www.cs.oberlin.edu/~rhoyle/16s-cs151/lab03/index.html
    private Square[][] mazeArraySquares;
    private Square start;
    private Square finish;
    private int numRows;
    private int numCols;
    private static final boolean DEBUG = false;


    // A constructor that takes no arguments
    // NOTE: you can just omit writing a constructor and Java will just use the default values, the real work will be done with the loadMaze() method described next.
    public Maze() // default values of nothing
    {
        mazeArraySquares = null;
        start = null;
        finish = null;
        numRows = 0;
        numCols = 0;
    }

    //load the maze that is contained in the file named fname. The format of the file is described above.
    public boolean loadMaze(String fname)
    {
        try (Scanner in = new Scanner(new File(fname)))
        {
            numRows = in.nextInt();
            numCols = in.nextInt();
            if(DEBUG) System.out.println(numRows+" "+numCols);
            mazeArraySquares = new Square[numRows][numCols];
            in.nextLine(); // move to actual maze
            for (int row=0; row < numRows; row++) {
                for (int col=0; col < numCols; col++) {
                    int current = in.nextInt();
                    if(DEBUG) System.out.println(current+"row:"+row+"col:"+col);
                    
                    Square newSquare = new Square(row,col,current);
                    mazeArraySquares[row][col] = newSquare;
                    if(current == Square.START)
                    {
                        start = newSquare;
                    }
                    else if (current == Square.EXIT)
                    {
                        finish = newSquare;
                    }
                }
            }

            if (DEBUG) System.out.println(Arrays.toString(mazeArraySquares));
            return true;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Cannot open: " + fname);
            System.out.println(e);
            return false;
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No Such element: " + fname +"\n File misformatted?");
            System.out.println(e);
            return false;
        }
    }

    //return an ArrayList of the Square neighbors of the parameter Square sq. There will be at most four of these (to the North, East, South, and West) and you should list them in that order.
    //If the square is on a border, skip over directions that are out of bounds of the maze. Don't be adding in null values.
    public ArrayList<Square> getNeighbors(Square sq)
    {
        ArrayList<Square> neighbors = new ArrayList<>();
        int squareRow = sq.getRow();
        int squareCol = sq.getCol();
        //north
        if(squareRow -1 >= 0)
            neighbors.add(mazeArraySquares[squareRow-1][squareCol]);
        //east
        if(squareCol + 1 < numCols)
            neighbors.add(mazeArraySquares[squareRow][squareCol+1]);
        //south 
        if(squareRow + 1 < mazeArraySquares.length)
            neighbors.add(mazeArraySquares[squareRow+1][squareCol]);
        // west
        if(squareCol -1 >=0)
            neighbors.add(mazeArraySquares[squareRow][squareCol-1]);
        return neighbors;
    }

        // Extension stuff
        public ArrayList<Square> getNeighborsWithNull(Square sq)
    {
        ArrayList<Square> neighbors = new ArrayList<>();
        int squareRow = sq.getRow();
        int squareCol = sq.getCol();
        //north
        if(squareRow -1 >= 0)
            neighbors.add(mazeArraySquares[squareRow-1][squareCol]);
        else
        {
            neighbors.add(null);
        }
        //east
        if(squareCol + 1 < numCols)
            neighbors.add(mazeArraySquares[squareRow][squareCol+1]);
        else
        {
            neighbors.add(null);
        }
        //south 
        if(squareRow + 1 < mazeArraySquares.length)
            neighbors.add(mazeArraySquares[squareRow+1][squareCol]);
        else
        {
            neighbors.add(null);
        }
        // west
        if(squareCol -1 >=0)
            neighbors.add(mazeArraySquares[squareRow][squareCol-1]);
        else
        {
            neighbors.add(null);
        }
        return neighbors;
    }

    /**Accessor methods that return the saved start/finish locations.*/
    public Square getStart()
    {
        return start;
    }
    
    //Accessor methods that return the saved start/finish locations.
    public Square getFinish()
    {   
        return finish;
    }
    
    //Return the maze back to the initial state after loading. Erase any marking on squares (e.g., visited or worklist) but keep the layout.
    public void reset()
    {
        for (int row=0; row < numRows; row++) {
            for (int col=0; col < numCols; col++) {
                mazeArraySquares[row][col].reset();
            }
        }
    }

    //return a String representation of this Maze in the format given below. (This is where it's useful to have a working Square.toString method.)
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Square[] rowSquares : mazeArraySquares){ // implicitly calls square tostring
            
            if(DEBUG)System.out.println(Arrays.toString(rowSquares));
            for(Square currSquare : rowSquares)
            {
                if(DEBUG) System.out.println("current"+currSquare.allToString());
                sb.append(currSquare);
            }
            sb.append("\n");
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length() - 1); // remove last \n
        }
        String s = new String(sb);
        return s;
    }

}
