package list;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 7. ListIterator [#350217]
 */
public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.add(index + 1, value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        var listIt = list.listIterator();
        while (listIt.hasNext()) {
            if (filter.test(listIt.next())) {
                listIt.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        var listIt = list.listIterator();
        while (listIt.hasNext()) {
            if (filter.test(listIt.next())) {
                listIt.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        var listIt = list.listIterator();
        while (listIt.hasNext()) {
            T element = listIt.next();
            for (T t : elements) {
                if (t.equals(element)) {
                    listIt.remove();
                    break;
                }
            }
        }
    }
}
