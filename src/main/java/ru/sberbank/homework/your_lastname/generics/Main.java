package ru.sberbank.homework.your_lastname.generics;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CustomCountMap<String> createCountMap = new CustomCountMap<>();
        createCountMap.add("qwe");
        createCountMap.add("www");
        createCountMap.add("qwe");
        createCountMap.add("qwe");
        createCountMap.add("www");
        createCountMap.add("111");
        createCountMap.add("qwwww");

        System.out.println("========Map1========");
        for (Map.Entry entry: createCountMap.toMap().entrySet()) {
            System.out.println(entry.getKey() + " value " + entry.getValue());
        }

        CountMap<String> cCountMap = new CustomCountMap<>();
        cCountMap.add("qwe");
        cCountMap.add("www");
        cCountMap.add("q12312");
        cCountMap.add("qwe");
        System.out.println("========Map2========");
        for (Map.Entry entry: cCountMap.toMap().entrySet()) {
            System.out.println(entry.getKey() + " value " + entry.getValue());
        }


        createCountMap.addAll(cCountMap);
        System.out.println("========ToMap1========");
        for (Map.Entry entry: createCountMap.toMap().entrySet()) {
            System.out.println(entry.getKey() + " value " + entry.getValue());
        }

        System.out.println("========ToMap2========");
        createCountMap.toMap(cCountMap.toMap());

        for (Map.Entry entry: cCountMap.toMap().entrySet()) {
            System.out.println(entry.getKey() + " value " + entry.getValue());
        }
    }
}
