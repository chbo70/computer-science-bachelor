package at.ac.uibk.pm.g06.csaz9837.s03.e06;

public class Position{
    private final String name;
    private final double longitude;
    private final double latitude;
    private double d;

    public Position(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double distance(Position otherPosition){
        //distance between latitudes (phi2 - phi1) and longitudes (delta2-delta1) for the formula
        double dLat = Math.toRadians(otherPosition.latitude - latitude);
        double dLon = Math.toRadians(otherPosition.longitude - longitude);
        //converting both latitudes to radian
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(otherPosition.latitude);

        //formula under the square
        double x = Math.pow(Math.sin(dLat/2),2) +
                   Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon/2),2);
        double radiusE = 6370;
        //final formula
        d = 2 * radiusE * Math.asin(Math.sqrt(x));
        return d;
    }
    public void printDistance(){
        if (!(d == 0)){
            System.out.println("\t distance = " + Math.round(d*100.0)/100.0 + " km");
        }
    }
    public void print(){
        //System.out.printf("%s: longitude = %f latitude = %f \n \t \t   distance = %.2f km\n", name, longitude, latitude, d);
        System.out.println(name + ": " + "longitude = " + longitude + " latitude = " + latitude);

    }


}
