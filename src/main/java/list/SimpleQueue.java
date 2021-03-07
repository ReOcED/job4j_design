package list;

/**
 * 5. Очередь на двух стеках [#160]
 * Fist in - First out;
 * @param <T>
 */

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Get the oldest element
     * @return last entered element
     */
    public T poll() {
        if (out.empty()) {
            while (!in.empty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Add new element
     * @param value
     */
    public void push(T value) {
        in.push(value);

    }
}
