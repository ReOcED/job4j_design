package iterators;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.4. FlatMap для Iterator<Iterator> [#152]
 * Итератор по итераторам.
 * @param <T>
 */
public class FlatMap<T> implements Iterator<T> {

    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

}
