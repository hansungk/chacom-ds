public interface Stack<E> {

	// Puts element on the top of this stack
	//
	public void push(E element);
	
	// Returns the top element from this stack
	// Raises an exception if the stack is empty and
	// pop is attempted
	//
	public E top() throws ESException;

	// Returns and removes the top element from this stack
	// Raises an exception if the stack is empty and
	// pop is attempted
	//
	public E pop() throws ESException;

	// Returns true if this stack is empty, and false otherwise.
	//
	public boolean isEmpty();

	// Returns the size of this stack
	//
	public int size();
}
