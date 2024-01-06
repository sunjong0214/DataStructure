import Interface_form.StackInterface;

public class StackExtendArrayLIst<E> extends ArrayList<E> implements StackInterface<E> {

  public StackExtendArrayLIst() {
    super();
  }

  public StackExtendArrayLIst(int capacity) {
    super(capacity);
  }

  @Override
  public E push(E item) {
    super.addLast(item);
    return item;
  }

  @Override
  public E pop() {
    int length = super.size();
    return (E) super.remove(length - 1);
  }

  @Override
  public E peek() {
    int length = super.size();
    return (E) super.get(length - 1);
  }

  @Override
  public int search(Object value) {
    int idx = super.lastindexOf(value);
    if (idx >= 0) {
      int size = size();
      return size - idx;
    }
    return -1;
  }

  @Override
  public boolean empty() {
    return size() == 0;
  }
}
