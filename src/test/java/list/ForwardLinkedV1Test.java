package list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ForwardLinkedV1Test {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardlinkedV1<Integer> linked = new ForwardlinkedV1<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardlinkedV1<Integer> linked = new ForwardlinkedV1<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardlinkedV1<Integer> linked = new ForwardlinkedV1<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

}