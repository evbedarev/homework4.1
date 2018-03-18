package ru.sberbank.homework.your_lastname.externalization;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class ExternalizeRouteService extends RouteService<City, Route<City>> {
    private HashMap<String, String> routeHashMap = new HashMap<>();
    private String newFilePath;
    private String uuid;
    private Long timeSerialize;
    private Long timeDeserialize;

    public ExternalizeRouteService(CachePathProvider cachePathProvider) {
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
            route = readExternalize(filePath);
        }
        return route;
    }

    HashMap<String, String> getRouteHashMap() {
        return routeHashMap;
    }

    private void externalize (List<City> cities, String filePath) {

        try {
            Long beginSerialize = System.currentTimeMillis();
            ExternalizeRoute fileExternalize = new ExternalizeRoute(cities, uuid);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
            fileExternalize.writeExternal(out);
            timeSerialize =System.currentTimeMillis()- beginSerialize;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private Route<City> readExternalize(String pathToFile) {

        try {
            Long beginDeserialize = System.currentTimeMillis();
            ExternalizeRoute fileExternalize = new ExternalizeRoute();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathToFile));
            fileExternalize.readExternal(in);
            Route<City> route = new Route<>(uuid,fileExternalize.getCityList());
            timeDeserialize = System.currentTimeMillis() - beginDeserialize;
            return route;

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
        externalize(cities, newFilePath);
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
