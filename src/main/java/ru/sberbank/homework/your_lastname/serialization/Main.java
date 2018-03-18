package ru.sberbank.homework.your_lastname.serialization;

import ru.sberbank.homework.your_lastname.externalization.ExternalizeRouteService;

public class Main {
    public static void main(String[] args) {

//        serialize();
        externalize();


    }

    private static void  serialize() {
        PathProvider pathProvider = new PathProvider("./src/main/bedarev");
        SerializeRouteService routeService = new SerializeRouteService(pathProvider);

        System.out.println(routeService.getRoute("Saint-Petersburg", "Astana").toString());
        System.out.println(routeService.getUuid());
        System.out.println("Time to serialize: " + routeService.getTimeSerialize());

        System.out.println(routeService.getRoute("Saint-Petersburg", "Astana").toString());
        System.out.println(routeService.getUuid());
        System.out.println("Time to deserialize: " +routeService.getTimeDeserialize());

        System.out.println(routeService.getRoute("Astana", "Saint-Petersburg").toString());
        System.out.println(routeService.getUuid());
        System.out.println("Time to serialize: " + routeService.getTimeSerialize());

        System.out.println(routeService.getRoute("Astana", "Saint-Petersburg").toString());
        System.out.println(routeService.getUuid());
        System.out.println("Time to deserialize: " +routeService.getTimeDeserialize());
    }

    private static void externalize(){
        PathProvider pathProvider = new PathProvider("./src/main/bedarev");
        ExternalizeRouteService routeService = new ExternalizeRouteService(pathProvider);

        System.out.println(routeService.getRoute("Saint-Petersburg", "Astana").toString());
        System.out.println(routeService.getUuid());
        System.out.println("Time to serialize: " + routeService.getTimeSerialize());

        System.out.println(routeService.getRoute("Saint-Petersburg", "Astana").toString());
        System.out.println(routeService.getUuid());
        System.out.println("Time to deserialize: " +routeService.getTimeDeserialize());

        System.out.println(routeService.getRoute("Astana", "Saint-Petersburg").toString());
        System.out.println(routeService.getUuid());
        System.out.println("Time to serialize: " + routeService.getTimeSerialize());

        System.out.println(routeService.getRoute("Astana", "Saint-Petersburg").toString());
        System.out.println(routeService.getUuid());
        System.out.println("Time to deserialize: " +routeService.getTimeDeserialize());
    }
}
