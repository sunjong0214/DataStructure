/* 
 * st-lab님의 블로그를 보며 따라하는 중 입니다.
 * 아래의 코드는 자바의 List Interface 입니다.
 * List는 ArrayList, SinglyLinkedList, DoublyLinkedList에 의해 각각 구현됩니다.
 * 
 */

public interface List<E> {

  boolean add(E value);
  
  boolean remove(Object value);

  boolean contains(Object value);

  int size();

  E get(int index);

  E set(int index, E value);

  boolean isEmpty();

  boolean equals(Object value);

  int indexOf(Object value);

  void clear();
}
