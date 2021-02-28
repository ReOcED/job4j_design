package gen.store;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    UserStore us;

    @Before
    public void init() {
        us = new UserStore();
    }

    @Test
    public void addGet() {
        User user = new User("id", "name", "sname");
        us.add(user);
        assertThat(us.findById("id"), is(user));
    }

    @Test
    public void addReplaceGet() {
        User user = new User("id", "name", "sname");
        us.add(user);
        User user1 = new User("id", "name1", "sname1");
        us.replace(user.getId(), user1);
        assertThat(us.findById("id"), is(user1));
    }

    @Test
    public void addDelete() {
        User user = new User("id", "name", "sname");
        us.add(user);
        us.delete(user.getId());
        assertNull(us.findById(user.getId()));
    }


}