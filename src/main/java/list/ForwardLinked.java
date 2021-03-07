package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 6. Перевернуть связанный список [#161]
 * @param <T>
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void revert() {
        head = revertRec(null, head, head.next);
    }

    private Node<T> revertRec(Node<T> prev, Node<T> curr, Node<T> next) {
        curr.next = prev;
        if (next == null) {
            return curr;
        }
        return revertRec(curr, next, next.next);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
