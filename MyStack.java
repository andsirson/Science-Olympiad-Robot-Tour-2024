import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyStack<T> implements StackADT<T> 
{
    private ArrayList<T> storage;

    public MyStack()
    {
        storage = new ArrayList<>();
    }
    
    public MyStack(ArrayList<T> otherArrayList)
    {
        storage = new ArrayList<>(otherArrayList);
    }

    /**
     * Add an item onto the stack
     * @param item the data item to add (of type T)
     */
    public void push(T item)
    {
        storage.add(item);
    }

    /**
     * Remove the top item from the stack
     * @return the top item in the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public T pop() throws NoSuchElementException
    {
        if(this.isEmpty())
        {
            throw new NoSuchElementException();
        }
        T object = storage.get(storage.size()-1);
        storage.remove(storage.size()-1);
        return object;
    }

    /**
     * Display the top item from the stack without removing it
     * @return the top item in the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public T top() throws NoSuchElementException
    {
        if(this.isEmpty())
        {
            throw new NoSuchElementException();
        }

        return storage.get(storage.size()-1);
    }

    /**
     * Find how many items are in the stack
     * @return the number of items in the stack
     */
    public int size()
    {
        return storage.size();
    }

    /**
     * Determine if the stack is empty
     * @return true if the size is 0, false otherwise
     */
    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    /**
     * Clear out the data structure
     */
    public void clear()
    {
        storage.clear();
    }
}
