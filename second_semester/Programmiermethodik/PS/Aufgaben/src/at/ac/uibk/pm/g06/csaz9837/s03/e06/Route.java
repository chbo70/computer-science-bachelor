package at.ac.uibk.pm.g06.csaz9837.s03.e06;

import java.util.ArrayList;

public class Route {
    private final ArrayList<Position> route;

    public Route(ArrayList<Position> route) {
        this.route = route;
    }
    //add new Destinations to the route
    public void addDestination(String name, double longitude, double latitude){
        Position nextPos = new Position(name, longitude, latitude);
        route.add(nextPos);
    }
    //gets the
    public Position getStart(){
        return route.get(0);
    }

    public Position getDestination(int next){
        return route.get(next);
    }

    public double getTravelDistance(){
        double totalDistance = 0;
        for (int i = 0; i < route.size(); ++i){
            if (i == route.size()-1){
                break;
            }
            totalDistance += route.get(i).distance(getDestination(i+1));
        }
        return totalDistance;
    }

    public void printRoute(){
        for (Position position : route) {
            position.print();
            position.printDistance();
        }
    }

}
