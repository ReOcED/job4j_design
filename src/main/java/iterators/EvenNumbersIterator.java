package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.2. Создать итератор четные числа [#150].
 * Итератор по int[], возвращающий только чётные числа.
 */

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int pointer = 0;

    public EvenNumbersIterator(final int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data.length != pointer && data[pointer] % 2 != 0) {
            pointer++;
        }
        return data.length != pointer;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[pointer++];
    }
}
