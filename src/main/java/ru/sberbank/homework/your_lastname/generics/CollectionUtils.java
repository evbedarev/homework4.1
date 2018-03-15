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
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<T> source, int size) {
        return source.stream().limit(size).collect(Collectors.toList());
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        for (T element:c2) {
            if (removeFrom.indexOf(element) > 0) {
                removeFrom.remove(element);
            }
        }
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);

    }
    //true если первый лист содержит хотя-бы 1 второго
    public static boolean containsAny(List c1, List c2) {
        return false;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static List range(List list, Object min, Object max) {
        return null;
    }

    public static List range(List list, Object min, Object max, Comparator comparator) {
        return null;
    }

}
