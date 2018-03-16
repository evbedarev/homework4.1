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
    private Map<T, Integer> countMap = new HashMap<>();

    public CreateCountMap() {
    }

    public void add(T o) {
        Long value = countMap.entrySet()
                .stream()
                .filter(elm -> elm == o)
                .count();
        if (value < 1) {
            value = 1L;
        }
        if (countMap.containsKey(o)) {
            countMap.entrySet()
                    .stream()
                    .filter(elm -> elm == o)
                    .forEach( e -> e.setValue(e.getValue() + 1) );
        }
        countMap.put(o, value.intValue());
    }

    public int getCount(T o) {
       return countMap.keySet()
               .stream()
               .filter( elm -> elm == o)
               .collect(Collectors.toList()).size();
    }

    public int remove(T o) {
        countMap.remove(o);

        countMap.entrySet()
                .stream()
                .filter(elm -> elm == o)
                .forEach( e -> e.setValue(e.getValue() - 1) );
        return getCount(o) + 1;
    }

    public  int size() {
        return countMap.size();
    }



}