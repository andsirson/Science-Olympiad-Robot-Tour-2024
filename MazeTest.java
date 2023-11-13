
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class MazeTest {
    @Test 
    public void testLoadMaze(){
        Maze maze = new Maze(); 
        assertEquals("Should be false", false, maze.loadMaze("askdjjhfklda"));
        assertEquals("Should be true", true, maze.loadMaze("src/maze-2")); 
        // note: if this fails, it could be because chromebooks or windows have different
        //file names. if this fails, go to your file and use relative path
    }
    @Test
    public void testGetStart(){ //  maze 1!
        Maze maze = new Maze(); 
        //needs to be added to EVERY TEST - checks if loadmaze works
        assertEquals(true,maze.loadMaze("src/maze-1"));

        Square sq  = new Square(0, 0, 2); 
        assertEquals(sq.getCol(), maze.getStart().getCol());
        assertEquals(sq.getRow(), maze.getStart().getRow());
        assertEquals(sq.getType(), maze.getStart().getType()); 
    }
    @Test
    public void testGetFinish(){
        Maze maze = new Maze(); 
        maze.loadMaze("src/maze-1"); 
        //needs to be added to EVERY TEST - checks if loadmaze works
        assertEquals(true,maze.loadMaze("src/maze-1"));
        Square sq  = new Square(2, 2, 3); 
        assertEquals(sq.getCol(), maze.getFinish().getCol());
        assertEquals(sq.getRow(), maze.getFinish().getRow());
        assertEquals(sq.getType(), maze.getFinish().getType());
    }
    @Test
    public void testToString(){ //  maze 2
        Maze maze = new Maze(); 
        maze.loadMaze("src/maze-2"); 
        //needs to be added to EVERY TEST - checks if loadmaze works
        assertEquals(true,maze.loadMaze("src/maze-2"));

        String x = """
______#______
##_#######_#_
_#_________#_
_#_##__###_#_
___#_____#_#_
_#####_#_#_#_
____S__#__#E_""";
        assertEquals(x, maze.toString());
    }
    
    @Test
    public void testgetNeighbors(){ // maze 1
        Maze maze = new Maze(); 
        maze.loadMaze("src\\maze-1"); 
        //needs to be added to EVERY TEST - checks if loadmaze works
        assertEquals(true,maze.loadMaze("src\\maze-1"));


        Square sqMid = new Square(1,1,0); 
        ArrayList<Square> ans = maze.getNeighbors(sqMid); 
        Square sqMidN = new Square(0,1,0); 
        assertEquals(sqMidN.getCol(), ans.get(0).getCol());
        assertEquals(sqMidN.getRow(), ans.get(0).getRow());
        assertEquals(sqMidN.getType(), ans.get(0).getType());
        Square sqMidE = new Square(1,2,0); 
        assertEquals(sqMidE.getCol(), ans.get(1).getCol());
        assertEquals(sqMidE.getRow(), ans.get(1).getRow());
        assertEquals(sqMidE.getType(), ans.get(1).getType());
        Square sqMidS = new Square(2,1,0); 
        assertEquals(sqMidS.getCol(), ans.get(2).getCol());
        assertEquals(sqMidS.getRow(), ans.get(2).getRow());
        assertEquals(sqMidS.getType(), ans.get(2).getType());
        Square sqMidW = new Square(1,0,0); 
        assertEquals(sqMidW.getCol(), ans.get(3).getCol());
        assertEquals(sqMidW.getRow(), ans.get(3).getRow());
        assertEquals(sqMidW.getType(), ans.get(3).getType());
        Square sqEdge = new Square(2,0,0); 
        ArrayList<Square> ansE = maze.getNeighbors(sqEdge);
        Square sqEN = new Square(1,0,0); 
        assertEquals(sqEN.getCol(), ansE.get(0).getCol());
        assertEquals(sqEN.getRow(), ansE.get(0).getRow());
        assertEquals(sqEN.getType(), ansE.get(0).getType()); 
        Square sqEE = new Square(2,1,0); 
        assertEquals(sqEE.getCol(), ansE.get(1).getCol());
        assertEquals(sqEE.getRow(), ansE.get(1).getRow());
        assertEquals(sqEE.getType(), ansE.get(1).getType());
    }
}
