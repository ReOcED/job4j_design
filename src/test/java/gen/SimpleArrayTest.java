package gen;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleArrayTest {
    SimpleArray<Integer> sa;

    @Before
    public void init() {
        this.sa = new SimpleArray<>(100);
    }

    @Test
    public void whenAddMultiThenGet() {
        sa.add(5);
        sa.add(10);
        sa.add(105);
        assertThat(sa.get(1), is(10));
    }

    @Test
    public void whenReplace() {
        sa.add(5);
        sa.add(10);
        sa.add(105);
        assertThat(sa.get(1), is(10));
        sa.set(1, 777);
        assertThat(sa.get(1), is(777));
    }

    @Test
    public void whenDeleteShouldMoveLeft() {
        sa.add(5);
        sa.add(10);
        sa.add(105);
        assertThat(sa.get(1), is(10));
        sa.remove(1);
        assertThat(sa.get(1), is(105));
    }

    @Test
    public void whenIteratorDoesntHaveNext() {
        assertFalse(sa.iterator().hasNext());
    }

    @Test
    public void whenIteratorHasNextThanGetNext() {
        sa.add(1);
        assertTrue(sa.iterator().hasNext());
        assertThat(sa.iterator().next(), is(1));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorReachesTheLast() {
        sa.add(1);
        Iterator<Integer> it = sa.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertFalse(it.hasNext());
        it.next();
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenDeleteShouldReduce() {
        sa.add(5);
        sa.add(10);
        sa.add(105);
        sa.remove(1);
        sa.get(2);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetWrongIndex() {
        sa.get(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenSetWrongIndex() {
        sa.set(1, 10);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenDeleteWrongIndex() {
        sa.remove(1);
    }

}