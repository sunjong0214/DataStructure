public class Node<E> {
  E data;
  Node<E> next;
  Node<E> prev;

  Node<E> (E data) {
    this.data = data;
    this.next = null;
    this.prev = null;
  }
}
