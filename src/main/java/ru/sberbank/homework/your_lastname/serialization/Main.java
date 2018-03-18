package ru.sberbank.homework.your_lastname.serialization;

public class Main {
    public static void main(String[] args) {
        PathProvider pathProvider = new PathProvider("./src/main/bedarev");
        SerializeRouteService routeService = new SerializeRouteService(pathProvider);

        System.out.println(routeService.getRoute("Saint-Petersburg", "Astana").toString());
        System.out.println(routeService.getUuid());
        System.out.println(routeService.getTimeSerialize());

        System.out.println(routeService.getRoute("Saint-Petersburg", "Astana").toString());
        System.out.println(routeService.getUuid());
        System.out.println(routeService.getTimeDeserialize());

        System.out.println(routeService.getRoute("Astana", "Saint-Petersburg").toString());
        System.out.println(routeService.getUuid());
        System.out.println(routeService.getTimeSerialize());

        System.out.println(routeService.getRoute("Astana", "Saint-Petersburg").toString());
        System.out.println(routeService.getUuid());
        System.out.println(routeService.getTimeDeserialize());
    }
}
