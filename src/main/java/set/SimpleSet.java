package set;

import java.util.*;

/**
 * 1. Реализовать коллекцию Set на массиве [#996]
 * @param <T>
 */

public class SimpleSet<T> implements Iterable<T> {

    private int size = 0;
    private int modCount = 0;
    private Node<T>[] data;

    @SuppressWarnings("unchecked")
    public SimpleSet() {
        data = (Node<T>[]) new Node[10];
    }

    /**
     * Add value.
     * Array should be resized, if more than 75% of the array is occupied;
     * Class can contains null;
     * Class can't contain duplicates.
     * @param value
     * @return successful true/false
     */
    public boolean add(T value) {
        boolean result;
        if (size > data.length * 3 / 4) {
           resizeData();
        }
        Node<T> n = new Node<>(value);
        result = this.add(n, chooseBucket(n.getHash()));
        if (result) {
            size++;
            modCount++;
        }
        return result;
    }

    private boolean add(Node<T> n, int bucket) {
        if (data[bucket] == null) {
            data[bucket] = getHead();
            data[bucket].setNext(n);
            return true;
        }
        Node<T> tail = bucketContains(data[bucket], n);
        if (tail == null) {
            return false;
        }
        tail.setNext(n);
        return true;
    }

    public boolean contains(T value) {
        Node<T> find = new Node<>(value);
        Node<T> cont = data[chooseBucket(find.getHash())];
        return cont != null && bucketContains(cont.getNext(), find) == null;
    }

    /**
     * Check, if current bucket contains value.
     * @param cont The first node of the bucket, that contains data (not head node);
     * @param value value to find
     * @return null if bucket contains value, tail node if not.
     */

    private Node<T> bucketContains(Node<T> cont, Node<T> value) {
        for (;; cont = cont.getNext()) {
            if (cont.equals(value)) {
                return null;
            }
            if (cont.getNext() == null) {
                break;
            }
        }
        return cont;
    }

    public boolean remove(T value) {
        Node<T> remove = new Node<>(value);
        int bucket = chooseBucket(remove.getHash());
        Node<T> cont = data[bucket];
        if (cont == null) {
            return false;
        }
        while (cont.getNext() != null) {
            if (cont.getNext().equals(remove)) {
                cont.setNext(cont.getNext().getNext());
                size--;
                modCount++;
                if (data[bucket].getNext() == null) {
                    data[bucket] = null;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Choose bucket for value by hash.
     * @param hash
     * @return number of bucket
     */
    private int chooseBucket(int hash) {
        return Math.abs(hash % data.length);
    }

    /**
     * Double data size.
     */
    @SuppressWarnings("unchecked")
    private void resizeData() {
        Node<T>[] oldData = data;
        data = (Node<T>[]) new Node[oldData.length * 2];
        for (Node<T> n : oldData) {
            while (n != null) {
                this.add(new Node<>(n.getData(), n.getHash()), chooseBucket(n.getHash()));
                n = n.getNext();
            }
        }

    }

    public int size() {
        return size;
    }

    /**
     * @return empty node for head node.
     */
    private Node<T> getHead() {
        return new Node<>(null);
    }



    public static void main(String[] args) {

        var hs = new HashSet<>();

        SimpleSet<Integer> ss1 = new SimpleSet<>();
        ss1.add(0);
        System.out.println(ss1.contains(null));

/*
        SimpleSet<String> ss = new SimpleSet<>();
        System.out.println(ss.contains(null));
        System.out.println(ss.add(null));
        System.out.println(ss.contains(null));
        System.out.println(ss.add(null));
        ss.add("1qwe1");
        ss.add("2qwe");
        ss.add("2qwe");
        ss.add("2qwe");
        ss.add("3dsddd");
        ss.add("41!23");
        ss.add("5dsadsdsвыв");
        ss.add(String.valueOf(6));
        ss.add(String.valueOf(7));
        ss.remove(null);
        var it = ss.iterator();
        System.out.println("=========");

        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("==========");
        System.out.println(ss.contains(null));
        System.out.println(ss.contains("41!23"));
        System.out.println(ss.contains("012"));*/

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int pointer = 0;
            private int currBucket = 0;
            private Node<T> currentElement;
            private int expectedModCount = modCount;

            /**
             * Set next not empty node.
             */
            private void setNext() {
                if (currentElement == null || currentElement.getNext() == null) {
                    do {
                        currentElement = data[currBucket++];
                    } while (currentElement == null);
                }
                currentElement = currentElement.getNext();

            }

            @Override
            public boolean hasNext() {
                return pointer < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                setNext();
                pointer++;
                return currentElement.getData();
            }
        };
    }

    private class Node<T> {
        private T data;
        private Node<T> next;
        private int hash = 0;


        public Node(T obj) {
            this.data = obj;
            if (obj != null) {
                hash = obj.hashCode();
            }
        }

        public Node(T obj, int hash) {
            this.data = obj;
            this.hash = hash;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

}
