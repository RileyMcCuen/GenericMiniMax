package utils.datastructures;

import java.util.Iterator;

import minimax.fourinarow.core.arrays.FourInARowGameState;
import minimax.fourinarow.core.arrays.FourInARowMove;
import minimax.fourinarow.performance.arrays.FourInARowPerformanceAgent;
import utils.implementation.AbstractGameState;

/**
 * Bare-bones implementation of doubly linked list with better node manipulation
 * than standard Linked List. To be used in LRUCache for checking recency.
 * 
 * @author Riley
 *
 * @param <G> - type of game-state to be stored in Map.
 */
public class DoublyLinkedList<G extends AbstractGameState<?>> implements Iterable<G> {

	private DoublyLinkedListNode<G> head, tail;

	@Override
	public Iterator<G> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addFirst(DoublyLinkedListNode<G> node) {
		node.setNext(head);
		if (head != null) {
			head.setPrev(node);
		}
		head = node;
		if (tail == null) {
			tail = head;
		}
	}

	public void addLast(DoublyLinkedListNode<G> node) {
		node.setPrev(tail);
		if (tail != null) {
			tail.setNext(node);
		}
		tail = node;
		if (head == null) {
			head = tail;
		}
	}

	public void deleteFromList(DoublyLinkedListNode<G> node) {
		DoublyLinkedListNode<G> prev = node.getPrev();
		DoublyLinkedListNode<G> next = node.getNext();
		node.setPrev(null);
		node.setNext(null);
		if (next != null && prev != null) {
			prev.setNext(next);
			next.setPrev(prev);
		} else if (next == null) {
			prev.setNext(null);
		} else if (prev == null) {
			next.setPrev(prev);
		}
	}

	public void updateNode(DoublyLinkedListNode<G> node) {
		deleteFromList(node);
		addLast(node);
	}

	public String toString() {
		StringBuilder listString = new StringBuilder();
		DoublyLinkedListNode<G> node = head;
		while (node != null) {
			listString.append(node.getValue().getPlyNumber());
			node = node.getNext();
		}
		return listString.toString();
	}

}
