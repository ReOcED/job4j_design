package gen;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 5.2.1. Реализовать SimpleArray<T> [#156]
 * сделать универсальную обертку над массивом.
 * Объект должен принимать количество ячеек. Структура не должна быть динамической.
 */

public class SimpleArray<T> implements Iterable<T> {

    private final T[] data;
    private int index = 0;

    @SuppressWarnings("unchecked")
    public SimpleArray(int size) {
        this.data = (T[]) new Object[size];
    }

    public void add(T model) {
        data[index++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, this.index);
        data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, this.index);
        System.arraycopy(data, index + 1, data, index, this.index - index);
        this.index--;
    }

    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return this.data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return pointer < index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[pointer++];
            }
        };
    }
}
