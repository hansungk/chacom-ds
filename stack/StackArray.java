public class StackArray<E> implements Stack<E> {

	private E[] data;
	private int top;

	static final int DEFAULT_CAPACITY = 2;

	public StackArray() {
		data = (E[]) new Object[DEFAULT_CAPACITY];

		// Number of elements in the stack
		top = -1;
	}

	public boolean isEmpty() {
		return ( (top + 1) == 0);
	}

	public void push(E element) {
		if((top + 1) == data.length) { expandArray(2); }
		data[++top] = element;
	}

	public E top() throws ESException {
		if(isEmpty()) { throw new ESException(); }
		return (E) data[ (top - 1) ];
	}

	public E pop() throws ESException {
		if(isEmpty()) { throw new ESException(); }
		return (E) data[ (top--) ];
	}

	public int size() {
		return (top + 1);
	}

	private void expandArray(int n) {
		System.out.println("Expanding array ...");
		E[] newArray = (E[]) new Object[n * data.length];
		for(int i = 0; i < (top + 1); i++) {
			newArray[i] = data[i];
		}
		data = newArray;
	}

	public String toString() {
		if(isEmpty()) {
			return "[]";
		}

		String result = "[ ";
		for (int i = 0; i < (top + 1); i++) {
			result += i + ":" + data[i] + ", ";
		}
		result += "]";
		return result;
	}
}
