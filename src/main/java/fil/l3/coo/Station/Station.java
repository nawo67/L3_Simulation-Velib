package fil.l3.coo.Station;

import fil.l3.coo.Vehicle.*;
import fil.l3.coo.ControlCenter.Observer;

import java.util.ArrayList;
import java.util.List;

// Represents a station of vehicles.
public class Station implements Observable {
    private int id; // The identifier of the station.
    private static int nextId = 1; // The next available identifier for a station.
    private int capacity; // The capacity of the station.
    private List<Vehicle> vehicles; // The list of vehicles in the station.
    private List<Observer> observers; // The list of observers for the station.

    /**
     * Constructs a station with the specified capacity.
     *
     * @param capacity The capacity of the station.
     */
    public Station(int capacity) {
        this.id = nextId++;
        this.capacity = capacity;
        this.vehicles = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Gets the id of the station.
     *
     * @return The identifier of the station.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the capacity of the station.
     *
     * @return The capacity of the station.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Gets the list of vehicles in the station.
     *
     * @return The list of vehicles in the station.
     */
    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
     * Gets the list of broken vehicles in the station.
     *
     * @return The list of broken vehicles in the station.
     */
    public List<Vehicle> getBrokenVehicles() {
        List<Vehicle> brokenVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getState() == VehicleState.BROKEN) {
                brokenVehicles.add(vehicle);
            }
        }
        return brokenVehicles;
    }

    /**
     * Gets the list of rental vehicles in the station.
     *
     * @return The list of rental vehicles in the station.
     */
    public List<Vehicle> getRentalVehicles() {
        List<Vehicle> rentalVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getState() == VehicleState.RENTAL) {
                rentalVehicles.add(vehicle);
            }
        }
        return rentalVehicles;
    }

    /**
     * Gets the list of available vehicles in the station.
     *
     * @return The list of available vehicles in the station.
     */
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getState() == VehicleState.AVAILABLE) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    /**
     * Adds a vehicle to the station if not full.
     *
     * @param vehicle The vehicle to be added.
     */
    public void addVehicle(Vehicle vehicle) {
        if (vehicles.size() < capacity) {
            vehicles.add(vehicle);
            notifyObservers();
         } else {
            System.out.println("Station is full");
        }
    }

    /**
     * Removes a vehicle from the station if not empty.
     *
     * @param vehicle The vehicle to be removed.
     */
    public void removeVehicle(Vehicle vehicle) {
        if (!vehicles.isEmpty()) {
            vehicles.remove(vehicle);
            notifyObservers();

        } else {
            System.out.println("Station is empty. Cannot remove vehicles.");
        }
    }

    /**
     * Checks if the station is full.
     *
     * @return True if the station is full, false otherwise.
     */
    public boolean isFull() {
        return vehicles.size() >= capacity;
    }

    /**
     * Checks if the station is empty.
     *
     * @return True if the station is empty, false otherwise.
     */
    public boolean isEmpty() {
        return vehicles.size() == 0;
    }

    /**
     * Checks if the station has only one vehicle.
     *
     * @return True if the station has only one vehicle, false otherwise.
     */
    public boolean hasOnlyOneVehicle() {
        return vehicles.size() == 1;
    }

    public boolean hasTooManyVehicles() {
        return vehicles.size() == (0.75 * capacity);
    }

    /**
     * Drops a vehicle to the station.
     *
     * @param vehicle The vehicle to be dropped.
     */
    public void dropVehicle(Vehicle vehicle) {
        try {
            if (!this.isFull()) {
                this.addVehicle(vehicle);
            } else {
                System.out.println("Station is full. Cannot drop the vehicle.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer The observer to be removed.
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers that the state of the station has changed.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Displays a description of the station.
     */
    public void getDescription() {
        String res = "The station's id is: " + getId() + " and its capacity is " + getCapacity();

        if (vehicles.isEmpty()) {
            res = res + "\nNo vehicles in the station.";
        } else {
            res = res + "\nVehicles in the station:";
            for (Vehicle vehicle : vehicles) {
                res = res + "\n" + vehicle.getDescription();
            }
        }
        System.out.println(res);
    }
}
