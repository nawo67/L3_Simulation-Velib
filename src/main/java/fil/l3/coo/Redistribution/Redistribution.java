package fil.l3.coo.Redistribution;

import fil.l3.coo.Station.Station;
import fil.l3.coo.Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

// Abstract class representing a redistribution strategy for vehicles among stations.
public abstract class Redistribution {
    protected List<Vehicle> waitingVehicles = new ArrayList<>(); // List to store vehicles temporarily while emptying stations.

    /**
     * Empties the stations by collecting all vehicles and moving them to a waiting list.
     *
     * @param theStations The list of stations to be emptied.
     */
    public void emptyTheStations(ArrayList<Station> theStations) {
        for (Station station : theStations) {
            List<Vehicle> vehicles = station.getVehicles();
            for (Vehicle vehicle : vehicles) {
                waitingVehicles.add(vehicle);
            }
        }
        for (Station station : theStations) {
            for (Vehicle vehicle : waitingVehicles) {
                if (station.getVehicles().contains(vehicle)) {
                    station.removeVehicle(vehicle);
                }
            }
        }
    }

    /**
     * Gets the list of waiting vehicles.
     *
     * @return The list of waiting vehicles.
     */
    public List<Vehicle> getWaitingVehicles() {
        return this.waitingVehicles;
    }

    /**
     * Adds waiting vehicles.
     *
     * @param vehicle The waiting vehicle.
     */
    public void addWaitingVehicles(Vehicle vehicle){
        this.waitingVehicles.add(vehicle);
    }

    /**
     * Performs the redistribution operation among the specified list of stations.
     *
     * @param theStations The list of stations to operate on.
     */
    public abstract void operate(ArrayList<Station> theStations);
}
