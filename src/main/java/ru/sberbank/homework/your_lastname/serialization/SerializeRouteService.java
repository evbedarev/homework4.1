package ru.sberbank.homework.your_lastname.serialization;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import java.io.*;
import java.util.*;


public class SerializeRouteService extends RouteService<City, Route<City>> {
    private HashMap<String, String> routeHashMap = new HashMap<>();
    private String newFilePath;
    private String uuid;
    private Long timeSerialize;
    private Long timeDeserialize;

    public SerializeRouteService(CachePathProvider cachePathProvider) {
        super(cachePathProvider, false);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;
        String filePath = routeHashMap.get(key);
        Route<City> route = null;

        if (filePath == null) {
            newFilePath = pathProvider.getCacheDirectoryPath() + File.separatorChar + from + "_" + to + ".dat";
            route = super.getRoute(from, to);
            routeHashMap.put(key, newFilePath);
        }

        if (filePath != null) {
            route = readSerialize(filePath);
        }
        return route;
    }

    HashMap<String, String> getRouteHashMap() {
        return routeHashMap;
    }

    private void serialize (String pathToFile, List<City> cities, String randomUUID ) {
        Long beginSerialize;
        try {
            beginSerialize = System.currentTimeMillis();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathToFile));

            out.writeObject(randomUUID);
            for (City city:cities) {
                out.writeObject(city);
            }
            out.close();
            timeSerialize =System.currentTimeMillis()- beginSerialize;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private Route<City> readSerialize(String pathToFile) {
        List<City> cities = new LinkedList<>();

        try {
            Long beginDeserialize = System.currentTimeMillis();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathToFile));
            String uuid = (String) in.readObject();

            while (true) {
                try {
                    City temp1 = (City) in.readObject();
                    cities.add(temp1);
                } catch (EOFException exception) {
                    break;
                }
            }
            timeDeserialize = System.currentTimeMillis() - beginDeserialize;
            return new Route<>(uuid, cities);

        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected Route<City> createRoute(List<City> cities) {
        uuid = UUID.randomUUID().toString();
        Route route = new Route<>(uuid, cities);
        serialize(newFilePath, cities, uuid);
        return route;
    }

    public String getUuid() {
        return uuid;
    }

    public Long getTimeSerialize() {
        return timeSerialize;
    }

    public Long getTimeDeserialize() {
        return timeDeserialize;
    }
}
