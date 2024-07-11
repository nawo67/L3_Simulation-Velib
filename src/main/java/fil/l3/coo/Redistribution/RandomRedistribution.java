package fil.l3.coo.Redistribution;

import fil.l3.coo.Station.Station;

import java.util.ArrayList;
import java.util.Random;

// Represents a redistribution strategy that randomly redistributes vehicles among stations.
public class RandomRedistribution extends Redistribution {

    /**
     * Operates the random redistribution strategy on the specified list of stations.
     *
     * @param theStations The list of stations to operate on.
     */
    @Override
    public void operate(ArrayList<Station> theStations) {

        System.out.println("Emptying the stations:");
        this.emptyTheStations(theStations);
        System.out.println("Stations emptied");

        int nbVehicles = waitingVehicles.size();

        for (int i = 0; i < nbVehicles; i++) {
            Station station = getRandomStation(theStations);
            while (station.isFull()) {
                station = getRandomStation(theStations);
            }
            station.addVehicle(waitingVehicles.get(i));
        }
    }

    /**
     * Gets a random station from the list of stations.
     *
     * @param theStations The list of stations.
     * @return A randomly selected station.
     */
    public Station getRandomStation(ArrayList<Station> theStations) {
        Random random = new Random();
        int randomIndex = random.nextInt(theStations.size());
        return theStations.get(randomIndex);
    }

    /**
     * Gets a string representation of the redistribution strategy.
     *
     * @return The string representation of the redistribution strategy.
     */
    @Override
    public String toString() {
        return "Random Redistribution";
    }
}
