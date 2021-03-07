package list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    SimpleLinkedList<String> sll;
    @Before
    public void init() {
        sll = new SimpleLinkedList<String>();
    }

    @Test
    public void whenAddThenGet() {
        String obj = "obj";
        sll.add(obj);
        assertThat(sll.get(0), is(obj));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetIncorrectIndex() {
        sll.add("obj");
        sll.get(1);
    }

    @Test
    public void whenSet() {
        String obj = "obj";
        String obj1 = "obj1";
        sll.add(obj);
        assertThat(sll.set(0, obj1), is(obj));
        assertThat(sll.get(0), is(obj1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenSetIncorrectIndex() {
        sll.add("obj");
        sll.set(1, "obj1");
    }

    @Test
    public void whenRemoveByIndex() {
        String obj = "obj";
        String obj1 = "obj1";
        sll.add(obj);
        sll.add(obj1);
        assertThat(sll.remove(0), is(obj));
        assertThat(sll.get(0), is(obj1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenRemoveIncorrectIndex() {
        sll.add("obj");
        sll.remove(1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenRemoveObj() {
        String obj = "obj";
        sll.add(obj);
        assertFalse(sll.remove("obj1"));
        assertTrue(sll.remove(obj));
        sll.get(0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorDoesntHaveNext() {
        var it = sll.iterator();
        assertFalse(it.hasNext());
        it.next();
    }

    @Test
    public void whenIteratorHasNextGetNext() {
        String obj = "obj";
        sll.add(obj);
        var it = sll.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(obj));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenListModifiesThanExeption() {
        var it = sll.iterator();
        sll.add("obj");
        it.next();
    }

    @Test
    public void whenRemoveByIterator() {
        String obj = "obj";
        String obj1 = "obj1";
        sll.add(obj);
        sll.add(obj1);
        var it = sll.iterator();
        it.next();
        it.remove();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(obj1));
    }
}