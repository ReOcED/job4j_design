package list;

import java.util.*;

/**
 * 2. Создать контейнер на базе связанного списка  [#159]
 * @param <T>
 */
public class SimpleLinkedList<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int index = 0;
    private int modCount = 0;

    public void add(T obj) {
        if (index == 0) {
            addFirst(obj);
        } else {
            Node<T> node = new Node<>(obj);
            node.setNext(last.getNext());
            node.setPrev(last);
            last.setNext(node);
            last = node;
            first.setPrev(last);
        }
        modCount++;
        index++;
    }

    private void addFirst(T obj) {
        first = new Node<>(obj);
        first.setNext(first);
        first.setPrev(first);
        last = first;
    }

    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return this.getIndexNode(index).getData();
    }

    public T set(int index, T obj) {
        Objects.checkIndex(index, this.index);
        Node<T> indexNode =  this.getIndexNode(index);
        T old = indexNode.getData();
        indexNode.setData(obj);
        modCount++;
        return old;
    }

    public T remove(int index) {
        Objects.checkIndex(index, this.index);
        return this.removeNode(this.getIndexNode(index));
    }

    public boolean remove(T obj) {
        int i = this.index;
        Node<T> current = this.last;
        while (i-- > 0) {
            if (current.getData().equals(obj)) {
                this.removeNode(current);
                return true;
            }
            current = current.getPrev();
        }
        return false;
    }


    private Node<T> getIndexNode(int index) {
        Node<T> resultNode = first;
        while (index-- > 0) {
            resultNode = resultNode.getNext();
        }
        return resultNode;
    }

    private T removeNode(Node<T> toRemove) {
        if (toRemove.equals(first)) {
            first = first.getNext();
        }
        toRemove.getNext().setPrev(toRemove.getPrev());
        toRemove.getPrev().setNext(toRemove.getNext());
        this.index--;
        this.modCount++;
        return toRemove.getData();
    }

    @Override
    public String toString() {
        Node<T> current = this.getIndexNode(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.index; i++) {
            sb.append(current.toString());
            if (i + 1 < index) {
                sb.append("; ");
            }
            current = current.getNext();
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return  new Iterator<T>() {
            private Node<T> current = first;
            private int expectedModCount = modCount;
            private int pointer = 0;

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
                T obj = current.getData();
                current = current.getNext();
                pointer++;
                return obj;
            }

            @Override
            public void remove() {
                checkModification();
                if (pointer == 0) {
                    throw new IllegalStateException();
                }
                removeNode(current.getPrev());
                pointer--;
                expectedModCount++;
            }


            private void checkModification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
