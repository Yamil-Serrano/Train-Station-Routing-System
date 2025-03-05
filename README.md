# Train Station Routing System

## Project Description
This project was developed for the CIIC4020/ICOM4035 Data Structures course during the Spring 2023-2024 semester as Project #2. The project simulates a train station routing system for Westside Central Station, focusing on finding the shortest routes between train stations and calculating travel times.

## Key Features
- Implementation of Dijkstra's algorithm to find shortest routes
- Parsing station connection data from CSV files
- Calculating travel times between stations
- Route tracing functionality

## Data Structures Used
- Map to represent station connections
- Stack for managing route discovery
- Custom data structures for Station and route management

## Core Algorithms
### Shortest Path Calculation
- Uses Dijkstra's algorithm to determine the most efficient route
- Starts from Westside station and finds shortest paths to all other stations
- Considers distance between stations

### Travel Time Calculation
- Calculates travel time based on:
  - 2.5 minutes per kilometer
  - 15 minutes for each station stop

## Project Components
1. **Station Class**
   - Represents individual train stations
   - Stores station name and distance information

2. **TrainStationManager Class**
   - Manages station connections
   - Implements route finding logic
   - Calculates travel times

## Input Data
- Reads station connections from `stations.csv`
- CSV file format: source city, destination city, distance

## Bonus Features
- Route tracing method
- Potential GUI implementation for station information and routing

## Technical Highlights
- Custom data structure implementation
- Relative file path handling
- Detailed route and time calculations

## Execution
1. Ensure `stations.csv` is in the `data` folder
2. Run `TrainStationManager` to process station connections
3. Use methods to retrieve shortest routes and travel times

## Development Considerations
- No use of Java Collection Framework built-in routing structures
- Focuses on custom data structure implementation
- Detailed documentation using JavaDoc

## Important Notes
- Academic project demonstrating data structures and algorithm implementation
- Simulates real-world routing and time calculation scenarios