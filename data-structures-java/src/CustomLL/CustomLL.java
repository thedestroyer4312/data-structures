package CustomLL;

/**
 * Custom implementation of the Java Collections LinkedList
 * @author Trevor Tsai
 * @version 4/7/20
 */
public class CustomLL<E> {
	class Node{
		Node previous;
		Node next;
		E val;

		Node(Node prev, Node next, E data){
			this.previous = prev;
			this.next = next;
			this.val = data;
		}
	}

	private Node head;
	private Node tail;
	private int size;

	public CustomLL(){
		this.head = new Node(null, null, null);
		this.tail = new Node(null, null, null);
		head.next = tail;
		tail.previous = head;
		size = 0;
	}

	// Mutable methods

	/**
	 * Adds a new element e to the end of the LinkedList
	 * @param e
	 */
	public void add(E e){
		tail.previous = new Node(tail, tail.previous, e);
		tail.previous.previous.next = tail.previous;
		size++;
	}

	//TODO: implement the methods below this line
	public void add(int index, E e){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
	}
	
	public boolean remove(E e){
		return false;
	}
	
	public void remove(int index){
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
	}
	//TODO: implement the methods above this line

	public void clear(){
		tail.previous = null;
		head.next = null;
		size = 0;
	}

	// Getter methods
	
	/**
	 * @return size of the linkedlist
	 */
	public int size(){
		return size;
	}

	/**
	 * @return true if size = 0, false otherwise
	 */
	public boolean isEmpty(){
		return size == 0;
	}
}
