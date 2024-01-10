import interface_form.QueueInterface;

public class ArrayQueue<E> implements QueueInterface<E> {
  private static final int DEFAULT_CAPACITY = 64;

  private Object[] array;
  private int size;
  private int front; // 시작 인덱스
  private int rear; // 마지막 요소의 인덱스

  public ArrayQueue() {
    this.array = new Object[DEFAULT_CAPACITY];
    this.size = 0;
    this.front = 0;
    this.rear = 0;
  }

  public ArrayQueue(int capacity) {
    this.array = new Object[capacity];
    this.size = 0;
    this.front = 0;
    this.rear = 0;
  }

  public void resize(int newCapacity) {
    int arrCapacity = array.length;
    Object[] newArray = new Object[newCapacity];

    for (int i = 1, j = front + 1; i <= size; i++, j++) {
      newArray[i] = array[j % arrCapacity];
    }
    array = null;
    array = newArray;

    front = 0;
    rear = size;
  }

  @Override
  public boolean offer(E item) {
    // if (front + 1 == rear || (rear == array.length - 1 && front == 0)) {
    // resize(array.length * 2);
    // }
    // if (rear == array.length-1) {
    // rear = 0;
    // }

    if ((rear + 1) % array.length == front) { // 용적이 가득찬 경우
      resize(array.length * 2);
    }

    rear = (rear + 1) % array.length;
    // 만약 rear가 끝에 도달했으면 = 0, 아니라면 용적보다 무조건 작으므로 그 값으로 나눴을 때 해당 인덱스가 나온다.

    array[rear] = item;
    size++;

    return true;
  }

  @Override
  public E poll(E value) {
    // if ((rear + 1) % array.length == front) {
    // resize(array.length * 2);
    // }
    // if (front == rear) {
    // return null;
    // }
    // E element = array[(front + 1 % array.length)];
    // array[(front + 1 % array.length)] = null;
    // front = (front + 1) % array.length;
    // size--;
    // return element;

    if (size == 0) {
      return null;
    }

    front = (front + 1) % array.length; // 한번만 계산하자.

    @SuppressWarnings("unchecked")
    E element = (E) array[front];
    array[front] = null;
    size--;

    if (array.length / 4 > size && array.length > DEFAULT_CAPACITY) {
      resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
    }
    return element;
  }

  public E remove() {

    E item = pull();

    if (item == null) {
      throw new NoSuchElementException();
    }

    return item;
  }

  @Override
  public E peek() {
    if (size == 0) {
      return null;
    }
    @SuppressWarnings("unchecked")
    E item = (E) array[(front + 1) % array.length];
    return item;
  }

  public E element() {
    E item = peek();
    if (item == null) {
      throw new NoSuchElementException();
    }
    return item;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(Object value) {
    int x = (front + 1) % array.length;

    for (int i = 0; i < size; i++) {

    }
  }
}
