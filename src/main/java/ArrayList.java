import java.util.Iterator;

/**
 * This class provides an implementation of a dynamic array
 */
public class ArrayList<T> implements Iterable<T>
{
    private T[] _items;
    private int _idx;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        _items = (T[])new Object[0];
    }

    public void add(T item) {
        final int len = _items.length;
        if (_idx == len) {
            _items = extend(_items, len == 0 ? 2 : len * 2);
        }

        _items[_idx++] = item;
    }

    public boolean remove(T item) {
        for (int i = 0; i < _idx; i++) {
            if (item == null && _items[i] == null) {
                removeIndex(i);
                return true;
            } else if (_items[i] != null && _items[i].equals(item)) {
                removeIndex(i);
                return true;
            }
        }

        return false;
    }

    public int length() {
        return _idx;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int _itemIdx;

            @Override
            public boolean hasNext() {
                return _itemIdx < _idx;
            }

            @Override
            public T next() {
                return _items[_itemIdx++];
            }
        };
    }

    private void removeIndex(int index) {
        // todo: this is unoptimal solution O(n)
        // it might be better if we could copy the whole memory block (as in C)
        // but Java does not provide unsafe operations
        for (int i = index + 1; i < _idx; i++) {
            swapElements(i, i - 1);
        }
        _idx--;
    }

    private void swapElements(int indexA, int indexB) {
        T tmp = _items[indexA];
        _items[indexA] = _items[indexB];
        _items[indexB] = tmp;
    }

    @SuppressWarnings("unchecked")
    private T[] extend(T[] source, int newLength) {
        T[] extended = (T[])new Object[newLength];
        copy(source, extended, source.length);

        return extended;
    }

    private void copy(T[] source, T[] dest, int count) {
        for (int i = 0; i < count; i++) {
            dest[i] = source[i];
        }
    }
}
