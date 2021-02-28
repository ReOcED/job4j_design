package gen.store;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(super.getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), firstName, secondName);
    }

    @Override
    public String toString() {
        return "User{" +
                super.getId() +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
