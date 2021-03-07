package list;

/**
 * 4. Используя контейнер на базе связанного списка создать контейнер Stack [#71474]
 * @param <T>
 */
public class SimpleStack<T> {
    private SimpleLinkedList<T> data = new SimpleLinkedList<>();

    public T pop() {
        return data.removeLast();
    }

    public void push(T value) {
        data.add(value);
    }
}
