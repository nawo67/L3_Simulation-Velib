package fil.l3.coo.Redistribution;

import fil.l3.coo.Station.Station;

import java.util.ArrayList;

// Represents a classical redistribution strategy that equitly redistributes vehicles among stations.
public class ClassicalRedistribution extends Redistribution {

    /**
     * Operates the classical redistribution strategy on the specified list of stations.
     *
     * @param theStations The list of stations to operate on.
     */
    @Override
    public void operate(ArrayList<Station> theStations) {

        this.emptyTheStations(theStations);
        int i = 0;
        int nbVehicles = waitingVehicles.size();
        int nbOfStations = theStations.size();

        for (int j = 0; j < nbVehicles; j++) {
            Station station = theStations.get(i % nbOfStations);
            if (station.hasTooManyVehicles()) {
                j--;
            } else {
                station.addVehicle(waitingVehicles.get(j));
            }
            i++;
        }

        waitingVehicles.clear();
    }
}
