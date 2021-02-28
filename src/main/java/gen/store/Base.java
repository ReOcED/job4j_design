package gen.store;

import java.util.Objects;

/**
 * 5.2.2. Реализовать Store<T extends Base> [#157]
 * Base model
 */

public abstract class Base {
    private final String id;

    public Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
