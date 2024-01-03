import java.util.NoSuchElementException;

import Interface_form.List;

public class SinglyLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList() {
      this.head = null;
      this.tail = null;
      this.size = 0;
    }

    private Node<E> search (int index) {
      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException();
      }
      Node<E> x = head;
      for (int i = 0; i < index; i++) {
        x = x.next;
      }
      return x;
    }

    public void addFirst(E value) {
      Node<E> temp = new Node<E>(value);
      temp.next = head;
      head = temp;
      size++;
      if(head.next == null) {
        tail = head;
      }
    }

    @Override
    public boolean add(E value) {
      addLast(value);
      return true;
    }
    public void addLast(E value) {
      Node<E> newNode = new Node<E>(value);

      if (head.next == null) {
        addFirst(value);
        return;
      }
      tail.next = newNode;
      tail = newNode;
      size++;
    }

    @Override
    public void add(int index, E value) {
      if (index < 0 || index > size) {
        throw new IndexOutOfBoundsException();
      }
      if (index == 0) {
        addFirst(value);
        return;
      }
      if (index == size) {
        add(value);
        return;
      }
      Node<E> preNode = search(index-1);
      Node<E> nexNode = preNode.next;
      Node<E> newNode = new Node<E>(value);
      preNode.next = null;
      newNode.next = nexNode;
      preNode.next = newNode;
      size++;
    }

    public E remove() {
      Node<E> delNode = head;
      Node<E> nexNode = head.next;
      if (delNode == null) {
        throw new NoSuchElementException();
      }
      E elementE = delNode.data;
      head.data = null;
      head.next = null;
      head = nexNode;
      size--;
      if (size == 0) {
        tail = null;
      }
      return elementE;
    }

    @Override
    public E remove(int index) {
      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException();
      }
      
    }
}
