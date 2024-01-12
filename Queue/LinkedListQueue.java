import java.util.NoSuchElementException;

import Interface_form.*;

public class LinkedListQueue<E> implements QueueInterface<E> {

  Node<E> head;
  Node<E> tail;
  int size;

  public LinkedListQueue() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  @Override
  public boolean offer(E item) {
    Node<E> addNode = new Node<E>(item);
    if (size == 0) {
      head = tail = addNode;
    } else {
      tail.next = addNode;
    }
    addNode = tail;
    size++;

    return true;
  }

  @Override
  public E poll() {
    Node<E> delNode = head;
    if (size == 0) {
      // throw new NoSuchElementException();
      return null; // poll의 경우 아무 요소가 없을 때 null을 반환한다.
    }
    E element = delNode.data;
    head = head.next;
    delNode.data = null;
    delNode.next = null;
    size--;
    if (head == null) {
      tail = null;
    }
    return element;
  }

  public E remove() {
    E element = poll();
    if (element = null) {
      throw new NoSuchElementException();
    }
    return element;
  }

  @Override
  public E peek() {
    if (size == 0) {
      return null;
    }
    E element = head.data;
    return element;
  }

  public E element() {
    E element = peek();
    if (element == null) {
      throw new NoSuchElementException();
    }
    return element;
  }
}
