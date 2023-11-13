import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.NoSuchElementException;

public class MyQueueTest {

    private MyQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new MyQueue<>();
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(1);
        queue.enqueue(2);
        Assert.assertEquals(2, queue.size());
    }

    @Test
    public void testDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        int dequeued = queue.dequeue();
        Assert.assertEquals(1, dequeued);
        Assert.assertEquals(1, queue.size());
    }

    @Test
    public void testDequeueEmptyQueue() {
        try {
            queue.dequeue();
            Assert.fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Test passed
        }
    }

    @Test
    public void testFront() {
        queue.enqueue(1);
        queue.enqueue(2);
        int front = queue.front();
        Assert.assertEquals(1, front);
    }

    @Test
    public void testFrontEmptyQueue() {
        try {
            queue.front();
            Assert.fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Test passed
        }
    }

    @Test
    public void testSize() {
        queue.enqueue(1);
        queue.enqueue(2);
        int size = queue.size();
        Assert.assertEquals(2, size);
    }

    @Test
    public void testSizeEmptyQueue() {
        int size = queue.size();
        Assert.assertEquals(0, size);
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(queue.isEmpty());
        queue.enqueue(1);
        Assert.assertFalse(queue.isEmpty());
    }

    @Test
    public void testClear() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.clear();
        Assert.assertEquals(0, queue.size());
    }
}
