import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Interface_form.List<E> {

  private Node<E> head;
  private Node<E> tail;
  private int size;

  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public Node<E> search(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    // if ((size / 2) < index) { // 뒤에서부터 탐색
    //   for (Node<E> x = tail; x != null; x = x.prev) {
    //     int i = size - 1;
    //     if (i-- == index) {
    //       return x;
    //     }
    //   }
    // } else {
    //   for (Node<E> x = head; x != null; x = x.next) {
    //     int i = 0;
    //     if (i++ == index) {
    //       return x;
    //     }
    //   }
    // }
    // 너무 Node를 이용한 조건문 사용에 매몰된 것 같다. 그리고 size도 인덱스보다 1 많으니 조건문 설정할 때 index + 1을 해줘야 정확한 비교 가능
    if (size / 2 < index + 1) {
      Node<E> x = tail;
      for (int i = size-1; i != index; i--) {
        x = x.prev;
      }
    } else {
      Node<E> x = head;
      for (int i = 0; i != index; i++) {
        x = x.next;
      }
    }
    return x;
  }

  public void addFirst(E value) {
    Node<E> addNode = new Node<E>(value);
    // if (head == null) {// 노드에 아무것도 없을 때
    //   head = addNode;
    //   tail = addNode;
    //   return;
    // }
    // Node<E> nextNode = head;
    // addNode.next = nextNode;
    // nextNode.prev = addNode;
    // head = addNode; 
    // 더 효율적으로 짜보자
    addNode.next = head;
    if (head != null) {
      head.prev = addNode;
    }
    head = addNode;
    size++;
    if (head == null) {
      tail = addNode;
    }
  }

  public boolean add(E value) {
    addLast(value);
    return true;
  }

  public void addLast(E value) {
    Node<E> newNode = new Node<E>(value);
    if (tail == null) {
      addFirst(value);
      return;
    }
    newNode.prev = tail;
    tail.next = newNode;
    tail = newNode;
    size++;
  }

  public void add(int index, E value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    if (index == 0) {
      addFirst(value);
      return;
    }
    if (index == size - 1) {
      addLast(value);
      return;
    }
    Node<E> newNode = new Node<E>(value);
    Node<E> preNode = search(index);
    Node<E> nxtNode = preNode.next;

    preNode.next = newNode;
    nxtNode.prev = newNode;
    newNode.prev = preNode;
    newNode.next = nxtNode;
    size++;
  }

  public E remove() {
    if (head = null) {
      throw new NoSuchElementException();
    }
    Node<E> rmNode = head;
    Node<E> nxtNode;
    if (rmNode.next != null) {
      nxtNode = rmNode.next;
      nxtNode = null; 
    }
    E element = rmNode.data;
    rmNode.data = null;
    rmNode.next = null;
    // rmNode.prev = null; // head를 가져왔기 때문에 이미 null이다.
    head = nxtNode;
    size--;
    // node가 하나만 있었을 경우 설정 또 깜빡함
    if (size == 0) {
      tail = null;
    }
    return element;
  }

  public E value(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    if (index == 0) {
      return remove();
    }
    Node<E> delNode = search(index);
    Node<E> nxtNode = delNode.next;
    Node<E> preNode = delNode.prev;

    E element = delNode.data;
    delNode.data = null;
    delNode.next = null;
    delNode.prev = null;
    preNode.next = nxtNode;

    if (nxtNode != null) {
      nxtNode.prev = preNode;
    } else {
      tail = preNode;
    }
    size--;
    return element;
  }

  public E remove(Object value) {

  }
}
