package gen.store;

import java.util.ArrayList;
import java.util.List;

/**
 * 5.2.2. Реализовать Store<T extends Base> [#157]
 * Base Storage.
 * @param <T>
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = mem.indexOf(this.findById(id));
        if (index >= 0) {
            mem.set(index, model);
        }
        return index >= 0;
    }

    @Override
    public boolean delete(String id) {
        return mem.removeIf(x -> x.getId().equals(id));
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}