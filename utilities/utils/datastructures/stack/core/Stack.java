package utils.datastructures.stack.core;

/**
 * Stack data structure.
 * 
 * @author Riley McCuen
 *
 */
public interface Stack<E, N extends SinglyLinkedNode<E, N>> extends Iterable<E> {

	public boolean empty();

	public E pop();

	public E peek();

	public void push(E value);

}
