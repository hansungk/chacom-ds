/* A circular array implementation of a queue

   Suppose the queue is of size 5 with the following elements.
   We will be using f and b as front and back indices into the
   array.

   front_of_queue <---   [ a | b | c | d | e ] <--- back_of_queue
                           f               b
                         
   Now, if you perform dequeue four times, the queue will look like:

   front_of_queue <---   [   |   |   |   | e ] <--- back_of_queue
                                           fb

   Now, if we try to enqueue, there is no place to add another
   element although there are four available slots.

   Well, a circular array implementation would solve this problem.
   We will make the array wrap around, i.e., when front reaches the
   end of the array, we will allow it to go to the beginning of the
   array when next enqueue request is made.

   Some interesting questions/cases to consider though:
   1. What is the relationship between front and back when the queue
      has one element?
      Both front and back point to the same slot.
   2. What is the relationship between front and back when the queue
      is empty?
   3. What about when the queue is full?
   4. When queue is empty or full, back should be (front - 1) in a
      wrapped around sense. 
   5. How can we tell a full queue from an empty queue?  Use the size info.
   6. For an enqueue request, we increment back and add the new element
      there.
   7. For an dequeue request, we return the value in the front location,
      and increment front.
 */

class QueueArray<E> implements Queue<E> {

    private E[] data;
    private int size, front, back;
    
    static final int DEFAULT_CAPACITY = 2;
    
    public QueueArray() {
	data = (E[])new Object[DEFAULT_CAPACITY];
	
	// Number of elements in the queue
	size = 0;
	front = 0;
	back = -1;
    }
    
    public boolean isEmpty() {
	return (size == 0);
    }
    
    private int increment (int i) {
	if ((i + 1) == data.length) {
	    return 0;
	}
	else {
	    return (i + 1);
	}
    }
    
    public void enqueue(E element) {
	if (size == data.length) {
	    expandArray(2);
	}
        back = increment(back);
	data[back] = element;
	size++;
    }
    
    public E dequeue() throws EQException {
	if (isEmpty()) {
	    throw new EQException();
	}
	size--;
	E returnValue = data[front];
	front = increment(front);
	return returnValue;
    }
    
    public E front() throws EQException {
	if (isEmpty()) {
	    throw new EQException();
	}
	return data[front];
    }
    
    public int size() {
	return size;
    }

    private void expandArray(int n) {

	System.out.println("Expanding array ...");
	E[] newArray = (E[])new Object[n * data.length];

	// Consolidate elements in the new array
	if (front <= back) {
	    // data array looks like this:
	    //  |o|o|o|o|o|o|o|o|o|o|o|o|o|
	    //   f                       b
	    for (int i = front; i <= back; i++) {
		newArray[i - front] = data[i];
		// newArray[i] = data[i];  // this works too
	    }
	}
	else if (back < front) {
	    // Looks like this:
	    //   |o|o|o|o|o|o|o|o|o|o|o|o|
	    //        b f
	    int i;
	    for (i = front; i < data.length; i++) {
		newArray[i - front] = data[i];
	    }
	    
	    for (int j = 0; j <= back; j++, i++) {
		newArray[i - front] = data[j];
	    }
	}
	data = newArray;
	front = 0;
	back = front + size - 1;
    }
    
    public String toString() {
	if (size == 0) {
	    return "[]";
	}
	String result = "[ ";
	if (front <= back) {
	    for (int i = front; i <= back; i++) {
		result += i + ":" + data[i] + ", ";
	    }
	}
	else {
	    for (int i = front; i < data.length; i++) {
		result += i + ":" + data[i] + ", ";
	    }
	    for (int i = 0; i <= back; i++) {
		result += i + ":" + data[i] + ", ";
	    }
	}
	result += "]";	
	return result;
    }

}

