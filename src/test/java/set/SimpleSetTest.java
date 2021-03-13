package set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

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

}