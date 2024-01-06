import git.Interface_form.List;

public class ArrayList implements List<E> {
  static final int DEFAULT_CAPACITY = 10;
  static final Object[] EMPTY_ARRAY = {};
  private int size;
  Object[] array;

  // 기본 용적을 지정하지 않은 경우
  public ArrayList() {
    this.array = EMPTY_ARRAY;
    this.size = 0;
  }

  // 기본 용적을 파라미터로 받은 경우
  public ArrayList(int capacity) {
    this.array = new Object[capacity];
    this.size = 0;
  }

  public void resize() {
    int array_capacity = array.length;

    // if (array_capacity == 0) {
    //   array = new Object[DEFAULT_CAPACITY];
    //   return;
    // }
    // 조건을 위와 같이 설정했을 땐, clear() 메소드를 사용했을 때 문제가 될 수 있다.
    // array의 내용은 비었지만, 길이는 늘어나있기 때문에 resize 했을 때 default 값으로 초기화가 안된다.
    if (Arrays.eqauls(array, EMPTY_ARRAY)) {
      array = new Object[DEFAULT_CAPACITY];
      return;
    }
    if (array_capacity == size) {
      int newSize = size * 2;
      
      array = Arrays.copyOf(array, newSize);
      return;
    }
    if (size < array_capacity /2) {
      int newSize = size / 2;
      
      array = Arrays.copyOf(array, Math.max(newSize, DEFAULT_CAPACITY));
      return;
    }
  }

  // ArrayList의 경우 add는 특정 위치를 지정하는 것이 아닌 이상 맨 뒤에 넣기 때문에 add의 기본은 addLast
  @Override
  public boolean add(E value) {
    addLast(value);
    return true;
  }

  public void addLast(E value) {
    if (array.length == size) {
      resize();
    }
    array[size] = value;
    size++;
  }

  public void add(int index, E value) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    if (index == size) {
      addLast(value);
    }
    else {
      if (array.length == size) {
        resize();
      }
      for (int i=size; i>index; i--) {
        array[i] = array[i-1];
      }
      array[index] = value;
      size++;
    }
  }
  public void addFirst(E value) {
    // if (array.length == size) {
    //   resize();
    // } add(index, value) 메소드에 이미 있음
    add(0, value);
  }

  @SuppressWarnings("unchecked")
  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    return (E) array[index];
  }

  @Override
  public void set(int index, E value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    array[index] = value;
  }

  @Override
  public int indexOf(E value) {
    for (int i = 0; i < size; i++) {
      if (array[i].equals(value)) {
        return i;
      }
    }
    return -1;
  }

  public int lastindexOf(E value) {
    for (int i = size-1; i >= 0; i--) {
      if (array[i].equals(value)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean contains(E value) {
    return indexOf(value) >= 0 ? true : false;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    E element = (E) array[index];
    for (int i = index; i < size-1; i++) {
      array[i] = array[i+1];
    }
    array[size-1] = null;
    size--;
    resize();
    return element;
  }

  @Override
  public boolean remove(E value) {
    return remove(indexOf(value)).eqauls(value) ? true : false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      array[i] = null;
      // add(i, null); 처음에 이렇게 작성했는데, 너무 메소드 재사용에 몰입한 것 같다. 
      //굳이 메소드를 사용할 필요없다. 시간복잡도가 올라간다.
    }
    size = 0;
    resize();
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    ArrayList<?> cloneList = (ArrayList<?>)super.clone();

    cloneList.array = new Object[size];

    // 배열 카피 - 원본 배열, 원본 배열 시작 위치, 복사할 배열, 복사할 배열 시작 위치, 복사할 요소 수
    System.arraycopy(array, 0, cloneList.array, 0, size);
    
    return cloneList; 
    //cloneList.array로 처음 작성했는데 좀 더 생각해보니 이 clone() 메소드 자체가 ArrayList의 모든 요소를 클론한다고 생각하니 size 등 변수들도 다 리턴하는 것이 맞는거 같다.
  }

  public Object[] toArray() {
    return Arrays.copyOf(array, size); //원본 배열에서 요소 개수만큼 복사해서 반환
  }

  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    if (a.length < size) {
      return (T[]) Arrays.copyOf(array, size, a.getClass());
      // 만약 a의 길이가 array의 길이보다 작으면 size에 맞게 공간을 재할당 하면서 요소들을 복사한다.
    }
    System.arraycopy(array, 0, a, 0, size);
    return a;
  }
}
