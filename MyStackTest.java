
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyStackTest {
    public ArrayList<String> testArray;
    private static final String TEST_STRING = "Alpha, Bravo, Charlie, Delta, Echo, Foxtrot, Golf, Hotel, India, Juliett, Kilo, Lima, Mike, November, Oscar, Papa, Quebec, Romeo, Sierra, Tango, Uniform, Victor, Whiskey, X-ray, Yankee, Zulu";
    private MyStack<String> testStack;

    @Before
    public void beforeFunction()
    {
        testStack = new MyStack<String>();
        testArray = new ArrayList<>(Arrays.asList(TEST_STRING.split(", ")));
    }

    /**
     * Add an item onto the stack
     * @param item the data item to add (of type T)
     */
    @Test
    public void testPush()
    {
        assertTrue(testStack.isEmpty());
        testStack.push(testArray.get(0));
        assertFalse(testStack.isEmpty());
        assertEquals(testArray.get(0),testStack.top());
        testStack.push(testArray.get(1));
        assertEquals(testArray.get(1),testStack.top());
    }

    /**
     * Remove the top item from the stack
     * @return the top item in the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Test
    public void testPop()
    {
        try {
            testStack.pop();
            fail("expected exception was not occured.");
        } catch(Exception e) 
        {
        }
        assertTrue(testStack.isEmpty());
        testStack.push(testArray.get(0));
        assertFalse(testStack.isEmpty());
        assertEquals(testArray.get(0),testStack.top());
        testStack.push(testArray.get(1));
        assertEquals(testArray.get(1),testStack.top());
        testStack.push(testArray.get(2));
        assertEquals(testArray.get(2),testStack.pop());
        assertEquals(testArray.get(1),testStack.pop());
        assertEquals(testArray.get(0),testStack.pop());
        try {
            testStack.pop();
            fail("expected exception was not occured.");
        } catch(Exception e) 
        {
        }
        assertTrue(testStack.isEmpty());
    }

    /**
     * Display the top item from the stack without removing it
     * @return the top item in the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Test
    public void testTop()
    {
        assertTrue(testStack.isEmpty());
        testStack.push(testArray.get(0));
        assertFalse(testStack.isEmpty());
        assertEquals(testArray.get(0),testStack.top());
        testStack.push(testArray.get(1));
        assertEquals(testArray.get(1),testStack.top());
    }

    /**
     * Find how many items are in the stack
     * @return the number of items in the stack
     */
    @Test
    public void testSize()
    {
        assertTrue(testStack.isEmpty());
        assertEquals(0,testStack.size());
        testStack.push(testArray.get(0));
        assertFalse(testStack.isEmpty());
        assertEquals(1,testStack.size());
        testStack.push(testArray.get(1));
        assertEquals(2,testStack.size());
        testStack.pop();
        testStack.pop();
        assertEquals(0,testStack.size());
    }

    /**
     * Determine if the stack is empty
     * @return true if the size is 0, false otherwise
     */
    @Test
    public void testIsEmpty()
    {
        assertTrue(testStack.isEmpty());
        testStack.push(testArray.get(0));
        assertFalse(testStack.isEmpty());
        testStack.push(testArray.get(1));
        assertFalse(testStack.isEmpty());
        testStack.pop();
        testStack.pop();
        assertTrue(testStack.isEmpty());

    }

    /**
     * Clear out the data structure
     */
    @Test
    public void testClear()
    {
        assertTrue(testStack.isEmpty());
        testStack.push(testArray.get(0));
        testStack.push(testArray.get(1));
        assertFalse(testStack.isEmpty());
        testStack.clear();
        try {
            testStack.pop();
            fail("expected exception was not occured.");
        } catch(Exception e) 
        {
        }
        assertTrue(testStack.isEmpty());
    }
}
