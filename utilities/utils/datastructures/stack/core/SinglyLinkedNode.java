package utils.datastructures.stack.core;

/**
 * Singly Linked List node that is used by the stack class.
 * 
 * @author Riley McCuen
 */

public interface SinglyLinkedNode<E, N extends SinglyLinkedNode<E, N>> {

	public N getNext();

	public E getData();

}
