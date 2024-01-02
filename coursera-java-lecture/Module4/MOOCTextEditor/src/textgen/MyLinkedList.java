package textgen;

import static org.junit.Assert.fail;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		
		LLNode<E> currNode = head.next;
		for (int i=0; i < this.size; i++) {
			if (currNode.data == element) {
				return false;
			}
			currNode = currNode.next;
		}
		
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = tail;
		tail.prev.next = newNode;
		newNode.prev = tail.prev;
		tail.prev = newNode;
		
		this.size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		// TODO: Implement this method.
		
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> currNode = head.next;
		for (int i=0; i < index; i++) {
			currNode = currNode.next;
		}
		
		return currNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> currNode = head.next;
		for (int i=0; i < index; i++) {
			if (currNode.data == element) {
				return;
			}
			currNode = currNode.next;
		}
		
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = currNode.prev.next;
		newNode.prev = currNode.prev;
		currNode.prev.next = newNode;
		currNode.prev = newNode;
		
		this.size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index)
	{
		// TODO: Implement this method
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> currNode = head.next;
		for (int i=0; i < index; i++) {
			currNode = currNode.next;
		}
		
		E removedData = currNode.data;
		currNode.prev.next = currNode.next;
		currNode.next.prev = currNode.prev;
		currNode.prev = null;
		currNode.next = null;
		currNode = null;
		
		this.size--;
		
		return removedData;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> currNode = head.next;
		for (int i=0; i < index; i++) {
			currNode = currNode.next;
		}
		
		E replacedData = currNode.data;
		currNode.data = element;
		
		return replacedData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
