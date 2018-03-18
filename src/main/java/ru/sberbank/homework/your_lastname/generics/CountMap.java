package ru.sberbank.homework.your_lastname.generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Дополнить. Параметризовать. Создать класс, реализующий интерфейс.
 */
public interface CountMap<T> {
    /**
     * добавляет элемент в этот контейнер.
     */
    void add(T o);

    /**
     * Возвращает количество добавлений данного элемента
     */
    int getCount(T o);

    /**
     * Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
     */
    int remove(T o);

    /**
     * количество разных элементов
     */
    int size();

    /**
     * Добавить все элементы из source в текущий контейнер, при совпадении ключей, суммировать значения
     */
    void addAll(CountMap<T> source);

    /**
     * Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
     */
    Map<T, Integer> toMap();

    /**
     * Тот же самый контракт как и toMap(), только всю информацию записать в destination
     */
    void toMap(Map<T, Integer> destination);

}

class CustomCountMap<T> implements CountMap<T> {
    private Map<T, Integer> countMap = new HashMap<>();

    CustomCountMap() {
    }

    public void add(T o) {
        if (countMap.containsKey(o)) {
            countMap.entrySet()
                    .stream()
                    .filter(elm -> elm.getKey().equals(o))
                    .forEach( e -> e.setValue(e.getValue() + 1) );
        }

        if (!countMap.containsKey(o)) {
            countMap.put(o, 1);
        }
    }

    public int getCount(T o) {

        return countMap.entrySet()
                .stream().filter( elm -> elm.getKey().equals(o))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(0);

    }

    public int remove(T o) {

        Integer valueCount = countMap.entrySet()
                .stream()
                .filter( elm -> elm.getKey().equals(o))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(0);

        if (valueCount == 1) {
            countMap.remove(o);
            return 1;
        }

        if (valueCount == 0) {
            return 0;
        }

        if (valueCount > 1)
        countMap.entrySet()
                .stream()
                .filter(elm -> elm.getKey() == o)
                .forEach( e -> e.setValue(e.getValue() - 1));


        return valueCount;
    }

    public  int size() {
        return countMap.keySet().size();

    }

    public void addAll(CountMap<T> source) {
        addMap(source.toMap(), countMap);
    }

    public Map<T, Integer> toMap() {
        return countMap;
    }

    public void toMap(Map<T, Integer> destination) {
        addMap(countMap, destination);
    }

    private void addMap(Map<T, Integer> source, Map<T, Integer> consumer) {
        for (Map.Entry<T, Integer> entry: source.entrySet()) {

            if (consumer.containsKey(entry.getKey())) {

                consumer.entrySet()
                        .stream()
                        .filter(elm -> elm.getKey() == entry.getKey())
                        .forEach(e -> e.setValue(e.getValue() + entry.getValue()));
            }

            if (!consumer.containsKey(entry.getKey())) {
                consumer.put(entry.getKey(), entry.getValue());
            }

        }

    }

}