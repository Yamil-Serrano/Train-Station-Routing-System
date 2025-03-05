package main;

/**
 * Represents a station with a city name and distance.
 */
public class Station {
    private String cityName; // The name of the city
    private int distance; // The distance of the station
	
    /**
     * Constructs a new Station with the specified city name and distance.
     *
     * @param name the name of the city
     * @param dist the distance of the station
     */
    public Station(String name, int dist) {
        cityName = name;
        distance = dist;
    }
	
    /**
     * Retrieves the name of the city associated with this station.
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }
	
    /**
     * Sets the name of the city associated with this station.
     *
     * @param cityName the new city name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
	
    /**
     * Retrieves the distance of this station from a reference point.
     *
     * @return the distance of the station
     */
    public int getDistance() {
        return distance;
    }
	
    /**
     * Sets the distance of this station from a reference point.
     *
     * @param distance the new distance of the station
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Station other = (Station) obj;
        return this.getCityName().equals(other.getCityName()) && this.getDistance() == other.getDistance();
    }
	
    /**
     * Returns a string representation of this station.
     *
     * @return a string representation of the station in the format "(cityName, distance)"
     */
    @Override
    public String toString() {
        return "(" + this.getCityName() + ", " + this.getDistance() + ")";
    }
}
