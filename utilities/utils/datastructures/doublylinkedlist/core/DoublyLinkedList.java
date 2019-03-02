package utils.datastructures.doublylinkedlist.core;

import java.util.Iterator;

/**
 * Bare-bones implementation of doubly linked list with better node manipulation
 * than standard Linked List. To be used in LRUCache for checking recency.
 * 
 * @author Riley McCuen
 *
 */
public class DoublyLinkedList<V extends Object, D extends DoublyLinkedListNode<V, D>> implements Iterable<D>{
	
	protected NodeSupplier<V, D> nodeSupplier;
	
	protected D head, tail;
	
	public DoublyLinkedList(NodeSupplier<V, D> nodeSupplier) {
		this.nodeSupplier = nodeSupplier;
	}

	public boolean empty() {
		return head == null;
	}
	
	public D getHead() {
		return this.head;
	}
	
	public void addLast(V value) {
		addLast(nodeSupplier.getNode(value));
	}

	public void addLast(D node) {
		node.setPrev(tail);
		if (tail != null) {
			tail.setNext(node);
		}
		tail = node;
		if (head == null) {
			head = tail;
		}
	}
	
	public D deleteHead() {
		if(head.getNext() != null) {
			D head = this.head;
			D next = this.head.getNext();
			this.head = next;
			next.setPrev(null);
			return head;
		} else {
			head = null;
			return null;
		}
	}

	public void deleteFromList(D node) {
		D prev = node.getPrev();
		D next = node.getNext();
		node.setNext(null);
		node.setPrev(null);
		if (next != null && prev != null) {
			if(node == head) {
				head = prev;
			}
			if(node == tail) {
				tail = next;
			}
			prev.setNext(next);
			next.setPrev(prev);
		} else if (prev != null) {
			if(node == head) {
				head = prev;
			}
			if(node == tail) {
				tail = prev;
			}
			prev.setNext(null);
		} else if (next != null) {
			if(node == head) {
				head = next;
			}
			if(node == tail) {
				tail = next;
			}
			next.setPrev(prev);
		} else {
			head = null;
			tail = null;
		}
	}

	public void updateNode(D node) {
		deleteFromList(node);
		addLast(node);
	}

	public String toString() {
		StringBuilder listString = new StringBuilder();
		D node = head;
		while (node != null) {
			listString.append(node.getData().toString() + " ");
			node = (D) node.getNext();
		}
		return listString.toString();
	}

	@Override
	public Iterator<D> iterator() {
		return new Iterator<D>() {

			private D node = head;
			
			@Override
			public boolean hasNext() {
				return node != null;
			}

			@Override
			public D next() {
				D ret = node;
				node = (D) node.getNext();
				return ret;
			}
			
		};
	}

}
