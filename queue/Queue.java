public interface Queue<E> {

    // Puts element at the back of this queue
    //
    public void enqueue(E element);


    // Returns the front element of this queue
    // Raises an exception if the queue is empty and
    // front is attempted
    //
    public E front() throws EQException;


    // Returns and removes the front element from this queue
    // Raises an exception if the queue is empty and
    // dequeue is attempted
    //
    public E dequeue() throws EQException;


    // Returns true if this queue is empty, and false otherwise.
    //
    public boolean isEmpty();


    // Returns the size of this queue
    //
    public int size();

}
