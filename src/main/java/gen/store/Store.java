package gen.store;

/**
 * 5.2.2. Реализовать Store<T extends Base> [#157]
 * Storage inteface
 * @param <T>
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
