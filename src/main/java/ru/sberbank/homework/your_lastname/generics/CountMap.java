package ru.sberbank.homework.your_lastname.generics;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    void toMap(Map destination);

}

class CreateCountMap <T> implements CountMap<T> {
    private Map<T, Long> countMap = new HashMap<>();

    public CreateCountMap() {
    }

    public void add(T o) {
        if (countMap.containsKey(o)) {
            countMap.entrySet()
                    .stream()
                    .filter(elm -> elm.getKey().equals(o))
                    .forEach( e -> e.setValue(e.getValue() + 1) );
        }

        if (!countMap.containsKey(o)) {
            countMap.put(o, 1L);
        }
    }

    public int getCount(T o) {
       return countMap.keySet()
               .stream()
               .filter( elm -> elm == o)
               .collect(Collectors.toList()).size();
    }

    public int remove(T o) {
        Long valueCount = countMap.entrySet()
                .stream().filter( elm -> elm.getKey().equals(o))
                .map(Map.Entry::getValue)
                .findFirst()
                .get();

        if (valueCount == 1L) {
            countMap.remove(o);
            return 0;
        }

        if (valueCount > 1L)
        countMap.entrySet()
                .stream()
                .filter(elm -> elm.getKey() == o)
                .forEach( e -> e.setValue(e.getValue() - 1));


        return valueCount.intValue() - 1;
    }

    public  int size() {
        return countMap.keySet().size();

    }



}