//package list;

public class LinkedList<E> implements List<E> {
	private Node<E> first, last;
	
	public LinkedList() {
		first = last = null;
	}
	
	public class Node <E> {
		private Node<E> prev, next;
		private E elem;
		
		public Node(E elem) {
			this.elem = elem;
			prev = next = null;
		}
		
		public Node<E> getPrev() {return prev;}
		public Node<E> getNext() {return next;}
		public void setPrev(Node<E> node) {prev = node;}
		public void setNext(Node<E> node) {next = node;}
		public E getElem() {return elem;}
		public E setElem(E elem) {return this.elem = elem;}
	}
	
	public E get(int index) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		return getNthNode(index).getElem();
	}
	
	public E set(int index, E element) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		return getNthNode(index).setElem(element);
	}
	
	public void add(int index, E element) {
		int numNodes = size();
		if(index < 0 || index > numNodes) throw new IndexOutOfBoundsException();
		
		Node<E> newNode = new Node<E>(element);
		if (numNodes == 0) {
			first = last = newNode;
		}
		else if (index == 0) {
			newNode.setNext(first);
			first.setPrev(newNode);
			first = newNode;
		}
		else if (index == numNodes) {
			newNode.setPrev(last);
			last.setNext(newNode);
			last = newNode;
		}
		else {
			// Adding to the left
			Node<E> rightNode = getNthNode(index);	
			newNode.setNext(rightNode);	
			newNode.setPrev(rightNode.getPrev());
			rightNode.getPrev().setNext(newNode);
			rightNode.setPrev(newNode);	
		}
	}
	
	public E remove(int index) {
		int numNodes = size();
		if(index < 0 || index >= numNodes) throw new IndexOutOfBoundsException();
		
		Node<E> removingNode = getNthNode(index);
		if (numNodes == 1) {
			first = last = null;
		}
		else if (removingNode == first) {
			first = removingNode.getNext();
			first.setPrev(null);
		}
		else if (removingNode == last) {
			last = removingNode.getPrev();
			last.setNext(null);
		}
		else {
			removingNode.getNext().setPrev(removingNode.getPrev());
			removingNode.getPrev().setNext(removingNode.getNext());
		}
		
		return removingNode.getElem();
	}
	
	public int size() {
		int n = 0;
		
		for(Node<E> node = first; node != null; node = node.getNext()) {
			n++;
		}
		return n;
	}
	
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	private Node<E> getNthNode(int n) {
		Node<E> node = first;
		for(int i=n; i>0; i--) node = node.getNext();
		return node;
	}
	
	// Tester
	
	public void printAll() {
		for(Node<E> node = first; node != null; node = node.getNext())
            System.out.print(node.getElem() + ", ");
        System.out.println();

	}
	
	public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<String>();

        ll.add(0, "John");
        ll.add(0, "Anna");
        ll.add(2, "Tommy");
        ll.add(1, "Guppy");

        ll.printAll();

        System.out.println(ll.get(2));

        System.out.println("removing " + ll.remove(2));

        ll.printAll();
        
        System.out.println("removing " + ll.remove(2));

        ll.printAll();
        
        System.out.println("removing " + ll.remove(0));

        ll.printAll();
        
        System.out.println("removing " + ll.remove(0));

        ll.printAll();
    }
}
