package set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    SimpleSet<String> ss;

    @Before
    public void init() {
        ss = new SimpleSet<>();
    }

    @Test
    public void whenAddShouldContain() {
        assertFalse(ss.contains("0"));
        ss.add("0");
        assertTrue(ss.contains("0"));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterator() {
        String msg = "msg";
        ss.add(msg);
        var it = ss.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals(msg, it.next());
        assertFalse(it.hasNext());
        it.next();
    }

    @Test
    public void whenAddAndRemoveNull() {
        assertFalse(ss.contains(null));
        ss.add(null);
        assertTrue(ss.contains(null));
        assertTrue(ss.remove(null));
        assertFalse(ss.contains(null));
    }

    @Test
    public void whenAddDuplicates() {
        String msg = "msg";
        ss.add(msg);
        ss.add(msg);
        var it = ss.iterator();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterIterator() {
        var it = ss.iterator();
        ss.add("");
        it.next();
    }

    @Test
    public void getSize() {
        assertThat(ss.size(), is(0));
        ss.add("1");
        ss.add("1");
        assertThat(ss.size(), is(1));
        ss.add("2");
        ss.add("3");
        ss.add("4");
        ss.add("5");
        ss.add("6");
        ss.add("7");
        ss.add("8");
        assertThat(ss.size(), is(8));
    }

}