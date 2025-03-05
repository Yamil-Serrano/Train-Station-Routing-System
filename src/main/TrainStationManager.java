package main;

import java.io.BufferedReader;
import java.io.FileReader;
import data_structures.ArrayList;
import data_structures.ArrayListStack;
import data_structures.HashSet;
import java.io.IOException;
import interfaces.HashFunction;
import data_structures.SimpleHashFunction;
import data_structures.HashTableSC;
import interfaces.Stack;
import interfaces.List;
import interfaces.Map;
import interfaces.Set;

/**
 * Manages train stations, their connections, and calculates travel times between them.
 */
public class TrainStationManager {
	private HashFunction<String> hash = new SimpleHashFunction<>();    
	private Map<String, List<Station>> stations = new HashTableSC<>(1,hash);
	private Map<String, Station> shortestDistance = new HashTableSC<>(1,hash);
	private Map<String, Double> travelTimes = new HashTableSC<>(1,hash);

	/**
     * Constructs a TrainStationManager and initializes station information from a file.
     *
     * @param station_file the file containing station information
     */
    public TrainStationManager(String station_file) {
        boolean firstLine = true;
        try (BufferedReader bufferReader = new BufferedReader(new FileReader("inputFiles/"+station_file))) {
            String line;
            while ((line = bufferReader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] tokens = line.split(",");
                String key = tokens[0].trim();
                Station value = new Station(tokens[1].trim(), Integer.parseInt(tokens[2].trim()));
                //if the key does not exist we create a key in the map and a empty list.
                if(!stations.containsKey(key)) {
                	stations.put(key, new ArrayList<>());
                }
                //depending to the key we now add more stations to its key.
                stations.get(key).add(value);
                
                // Now the same process as before but other way around
                String key2 = tokens[1].trim();
                Station value2 = new Station(tokens[0].trim(), Integer.parseInt(tokens[2].trim()));
                if(!stations.containsKey(key2)) {
                	stations.put(key2, new ArrayList<>());
                }
                stations.get(key2).add(value2);
            }
            findShortestDistance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    /**
     * Finds the shortest distance between train stations using Dijkstra's algorithm.
     */
	private void findShortestDistance() {
		Stack<Station> toVisitStack = new ArrayListStack<>();
		Set<String> visited = new HashSet<>();
		for(String station : stations.getKeys()) {
			if(!shortestDistance.containsKey(station)) {
			shortestDistance.put(station, new Station("Westside", Integer.MAX_VALUE));
			}
		}
		toVisitStack.push(new Station ("Westside", 0));
		shortestDistance.get(toVisitStack.top().getCityName()).setDistance(0);
		while(!toVisitStack.isEmpty()) {
			Station visiting = toVisitStack.pop();
			visited.add(visiting.getCityName());
			
			for(Station neighbors : stations.get(visiting.getCityName())) {
				int newDistance = neighbors.getDistance() + shortestDistance.get(visiting.getCityName()).getDistance();
				if(shortestDistance.get(neighbors.getCityName()).getDistance() > newDistance) {
					shortestDistance.get(neighbors.getCityName()).setDistance(newDistance);
					shortestDistance.get(neighbors.getCityName()).setCityName(visiting.getCityName());
				if(!visited.isMember(neighbors.getCityName())) {
					sortStack(neighbors, toVisitStack);
					}
				}
			}
			
		}
	}

	/**
     * Sorts a stack of stations based on their distances.
     *
     * @param station      the station to be sorted
     * @param stackToSort  the stack of stations to be sorted
     */
	public void sortStack(Station station, Stack<Station> stackToSort) {
		 Stack<Station> tempStack =  new ArrayListStack<>();
		    if (stackToSort.isEmpty()) {
		        stackToSort.push(station);
		        return;
		    }
		    while (!stackToSort.isEmpty() && stackToSort.top().getDistance() < station.getDistance()) {
		        tempStack.push(stackToSort.pop());
		    }
		    stackToSort.push(station);
		    while (!tempStack.isEmpty()) {
		        stackToSort.push(tempStack.pop());
		    }
	}
	
	/**
     * Retrieves the travel times between train stations.
     *
     * @return a map containing travel times between train stations
     */
	public Map<String, Double> getTravelTimes() {
		for(String stations : shortestDistance.getKeys()) {
			if((shortestDistance.get(stations).getCityName()).equals(stations)) {
				travelTimes.put(stations, 0.0);
			}
			else {
				int stationsCounter = 0;
				String currentStation = stations;
				while(!(((shortestDistance.get(currentStation).getCityName()).equals(shortestDistance.getKeys().get(0))))) {
					currentStation = shortestDistance.get(currentStation).getCityName();
					stationsCounter++;
				}
				travelTimes.put(stations, (shortestDistance.get(stations).getDistance() * 2.5) + (15 * stationsCounter));
			}
		}
		return travelTimes;
	}

	 /**
     * Retrieves the map of train stations and their connections.
     *
     * @return a map containing train stations and their connections
     */
	public Map<String, List<Station>> getStations() {
		return stations;
	}

	public Map<String, Station> getShortestRoutes() {
		return shortestDistance;
	}
	
	/**
	 * BONUS EXERCISE THIS IS OPTIONAL
	 * Returns the path to the station given. 
	 * The format is as follows: Westside->stationA->.....stationZ->stationName
	 * Each station is connected by an arrow and the trace ends at the station given.
	 * 
	 * @param stationName - Name of the station whose route we want to trace
	 * @return (String) String representation of the path taken to reach stationName.
	 */
	public String traceRoute(String stationName) {
		// Remove if you implement the method, otherwise LEAVE ALONE
		String route = "";
		String currentStation = null;
		boolean mainIsAdded = false;
		Stack<String> stationOrder = new ArrayListStack<>();
		for(String stations : shortestDistance.getKeys()) {
			if(stations.equals(stationName)) {
				currentStation = stations;
				if(currentStation == shortestDistance.getKeys().get(0)) {
					mainIsAdded = true;
				}
				break;
			}
		}
		stationOrder.push(currentStation);
		while(!(((shortestDistance.get(currentStation).getCityName()).equals(shortestDistance.getKeys().get(0))))) {
			currentStation = shortestDistance.get(currentStation).getCityName();
			stationOrder.push(currentStation);
		}
		if(!mainIsAdded) {
			stationOrder.push(shortestDistance.getKeys().get(0));
		}
		while(!stationOrder.isEmpty()) {
			if(stationOrder.size() == 1) {
				route += stationOrder.pop();
				}
			else {
			route += stationOrder.pop() + "->";
			}
		}
		return route;
	}

}