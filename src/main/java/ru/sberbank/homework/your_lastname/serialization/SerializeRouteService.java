package ru.sberbank.homework.your_lastname.serialization;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import java.io.*;
import java.util.*;


public class SerializeRouteService extends RouteService<City, Route<City>> {
    private HashMap<String, String> routeHashMap = new HashMap<>();

    public SerializeRouteService(CachePathProvider cachePathProvider) {
        super(cachePathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        Route<City> route = super.getRoute(from, to);
        routeHashMap.put(key, "serialize.dat");
        return route;
    }

    HashMap<String, String> getRouteHashMap() {
        return routeHashMap;
    }

    private void serialize (String pathToFile, List<City> cities ) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathToFile));
            for (City city:cities) {
                out.writeObject(city);
            }
            System.out.println("Write class to file: " + pathToFile);
            out.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void readSerilize (String pathToFile) {
        try {
            ObjectInputStream in =  new ObjectInputStream (new FileInputStream(pathToFile));
            while (true) {
                try {
                    City temp1 = (City) in.readObject();
                    System.out.println(temp1.toString());
                } catch (EOFException exception) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected Route<City> createRoute(List<City> cities) {
        Route route = new  Route<>(UUID.randomUUID().toString(), cities);
        serialize("serialize.dat", cities);
        return route;
    }
}
