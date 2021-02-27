package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1. Что такое итератор. [#4951]
 * Итератор, возвращающий элементы в обратном порядке.
 */

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int pointer = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pointer < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[data.length - ++pointer];
    }
}
