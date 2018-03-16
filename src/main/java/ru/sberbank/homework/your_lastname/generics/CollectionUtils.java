package ru.sberbank.homework.your_lastname.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Параметризовать методы, используя правило PECS, и реализовать их.
 */

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
        System.out.println("destination " + destination.get(0));
        System.out.println("source " + destination.get(0));
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? super T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        return source.stream()
                .limit(size)
                .collect(Collectors.toList());
    }


    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        for (T element:c2) {
            if (removeFrom.contains(element)) {
                removeFrom.remove(element);
            }
        }
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);

    }

    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T element:c2) {
            if (c1.contains(element)) {
                return true;
            }
        }
        return false;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}


    public static <T extends Comparable<T>> List<T> range(List<? extends T> list, T min, T max) {
        return list.stream()
                .sorted()
                .filter(elm -> elm.compareTo(min) >= 0)
                .filter(elm -> elm.compareTo(max) <= 0)
                .collect(Collectors.toList());
    }

    public static <T extends Comparable<T>> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        return list.stream()
                .sorted()
                .filter(elm -> comparator.compare(elm,min) >= 0)
                .filter(elm -> comparator.compare(elm,max) <= 0)
                .collect(Collectors.toList());
    }
}
