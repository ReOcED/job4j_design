package gen.store;

/**
 * 5.2.2. Реализовать Store<T extends Base> [#157]
 * User class
 */
public class User extends Base {

    private String firstName;
    private String secondName;

    public User(String id, String firstName, String secondName) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
    }

}
