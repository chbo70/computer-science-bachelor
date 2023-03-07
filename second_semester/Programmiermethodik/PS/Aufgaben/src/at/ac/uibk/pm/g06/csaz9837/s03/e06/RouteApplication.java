package at.ac.uibk.pm.g06.csaz9837.s03.e06;

import java.util.ArrayList;

public class RouteApplication {
    public static void main(String[] args) {
        System.out.println("Start");
        System.out.println("-----");

        ArrayList<Position> travel = new ArrayList<>();
        Route route = new Route(travel);
        route.addDestination("Innsbruck", 11.39454, 47.26266);
        route.addDestination("Vienna", 16.37208, 48.20849);
        route.addDestination("Berlin", 13.404954, 52.520008);
        route.addDestination("Paris", 2.349014, 48.864716);
        route.addDestination("Innsbruck", 11.39454, 47.26266);
        route.getTravelDistance();
        route.printRoute();

        System.out.println("-----");
        System.out.println("End");
        System.out.println("Total distance = " + Math.round(route.getTravelDistance()*100.0)/100.0 + " km");
    }
}
