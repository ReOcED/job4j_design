package list;

import java.util.*;

/**
 * 1. Динамический список на массиве. [#158]
 * @param <T>
 */
public class SimpleArrayList<T> implements Iterable<T> {

    private T[] data;
    private int index = 0;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public SimpleArrayList() {
        this.data = (T[]) new Object[10];
    }

    public void add(T model) {
        if (index == data.length - 1) {
            this.data = Arrays.copyOf(this.data, index * 2);
        }
        data[index++] = model;
        modCount++;
    }

    public void update(T obj, int index) {
        Objects.checkIndex(index, this.index);
        this.data[index] = obj;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return data[index];
    }

    public boolean remove(T obj) {
        boolean result = false;
        for (int i = 0; i <= index; i++) {
            if (obj.equals(data[i])) {
                System.arraycopy(data, i + 1, data, i, index - i);
                index--;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    public T remove(int index) {
        Objects.checkIndex(index, this.index);
        T obj = data[index];
        System.arraycopy(data, index + 1, data, index, this.index - index);
        modCount++;
        this.index--;
        return obj;
    }

    @Override

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pointer = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return pointer < index;
            }

            @Override
            public T next() {
                checkModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[pointer++];
            }

            @Override
            public void remove() {
                checkModification();
                if (pointer == 0) {
                    throw new IllegalStateException();
                }
                SimpleArrayList.this.remove(--pointer);
                expectedModCount--;
            }

            private void checkModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
