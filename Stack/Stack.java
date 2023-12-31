import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;

import Interface_form.StackInterface;

public class Stack<E> implements StackInterface<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private static final Object[] EMPTY_ARRAY = {};

  private Object[] array;
  private int size = 0;

  public Stack() { // 초기 공간 할당 x
    this.array = EMPTY_ARRAY;
    this.size = 0;
  }

  public Stack(int capacity) { // 초기 공간 할당 o
    this.array = new Object[capacity];
    this.size = 0;
  }

  private void resize() {
    // 빈 배열일 경우
    if (Arrays.equals(array, EMPTY_ARRAY)) {
      array = new Object[DEFAULT_CAPACITY];
      return;
    }
    // 요소가 용적 / 2 보다 작을경우
    if (size < array.length / 2) {
      array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, (array.length / 2)));
      // Math.max는 크기가 DEFAULT_CAPACIYT보다 작아지는 상황을 방지하기 위해 사용
      return;
    }
    // 요소가 용적의 크기와 같을 경우
    if (size == array.length) {
      array = Arrays.copyOf(array, (array.length / 2));
      return;
    }
  }

  @Override
  public E push(E item) {
    if (array.length == size) {
      resize();
    }
    array[size] = (E) item;
    size++;

    return item;
  }

  @Override
  public E pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    @SuppressWarnings("unchecked")
    E element = (E) array[size - 1];
    array[size - 1] = null;
    size--;
    resize();
    return element;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E peek() {
    if (size == 0) {
      throw new EmptyStackException();
    }

    return (E) array[size - 1];
  }

  @Override
  public int search(Object value) {
    // value가 null일 때는 eqauls(null)이되어 null pointer exception이 발생할 수 있으니,
    // == 로 null값을 비교해준다.
    // 왜 null 조회가 필요한지는 나중에 알아보자
    if (value == null) {
      for (int i = size - 1; i >= 0; i--) {
        if (arr[i] == value) {
          return size - i;
        }
      }
    } else {
      for (int i = size - 1; i >= 0; i--) {
        if (arr[i] == value) {
          return size - i;
        }
      }
    }
    return -1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      array[i] = null;
    }
    size = 0;
    resize(); 
    // resize 시 배열의 값이 아무것도 없어도 Arrays.equals(array, DEFAULT_ARRAY) 조건에 충족되지 않아 사이즈가 절반으로 줄임
  }

  @Override
  public boolean empty() {
    return size == 0;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    Stack<E> clone = (Stack<E>) super.clone();

    // clone.DEFAULT_CAPACITY = 10;
    // clone.EMPTY_ARRAY = {};
    // clone.array;
    // clone.size = 0;

    clone.array = new Object[size];

    // for (int i = 0; i < size; i++) {
    //   clone.array[i] = array[i];
    //   clone.size++;
    // }
    System.arraycopy(clone, 0, array, 0, size);
    return clone();
  }

  public Object toArray() {
    // return  array;
    // array의 용적이 size보다 크므로 size 만큼의 크기를 가지는 새로운 배열을 리턴해주자
    return Arrays.copyOf(array, size);
  }

  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    if (a.length < size) {
      //a = (T[]) Array.newInstance(getClass().getComponentType(), size);
      return Arrays.copyOf(array, size, getClass());
    }
    System.arraycopy(array, 0, a, 0, size);
    return a;
  }

  public void sort() {
    sort(null);
  }

  public void sort(Comparator<? super E> c) {
    // Arrays.sort(array, (Comparator) c);
    Arrays.sort((E[]) array, 0, size, c);
    // size 까지만 정려하면 되기 때문에 위  Arrays.sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) 를 사용
  }
}
