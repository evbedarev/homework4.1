package ru.sberbank.homework.your_lastname.serialization;

public class Main {
    public static void main(String[] args) {
        PathProvider pathProvider = new PathProvider();
        SerializeRouteService routeService = new SerializeRouteService(pathProvider);
        routeService.getRoute("Saint-Petersburg", "Astana");
        routeService.readSerilize("serialize.dat");

    }
}
