package fil.l3.coo.Simulation;

import fil.l3.coo.Station.Station;
import fil.l3.coo.ControlCenter.ControlCenter;
import fil.l3.coo.Visitor.*;
import fil.l3.coo.User.User;
import fil.l3.coo.Vehicle.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Represents a global simulation of a bike-sharing system, including stations, vehicles, users, and various operations.
public class GlobalSimulation {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // Scheduler for periodic updates.
    private List<Station> stations = new ArrayList<>(); // List of stations in the simulation.
    private ControlCenter controlCenter; // Control center managing the bike-sharing system.
    private Repairer repairer; // Repairer responsible for vehicle repairs.
    private Thief thief; // Thief responsible for stealing vehicles.
    private List<User> users = new ArrayList<>(); // List of users participating in the simulation.

    /**
     * Constructs a global simulation with the specified control center and users.
     *
     * @param controlCenter The control center managing the bike-sharing system.
     * @param users         The list of users participating in the simulation.
     */
    public GlobalSimulation(ControlCenter controlCenter, List<User> users) {
        this.stations = controlCenter.getObservedStations();
        this.controlCenter = controlCenter;
        this.repairer = new Repairer(this.controlCenter);
        this.thief = new Thief(this.controlCenter);
        this.users = users;
    }

    /**
     * Starts the simulation by scheduling periodic updates.
     */
    public void startSimulation() {
        displaySimulationState();
        scheduler.scheduleAtFixedRate(() -> {
            simulateOperations();
        }, 0, 2, TimeUnit.SECONDS);
    }

    /**
     * Simulates various operations such as vehicle rental, return, redistribution, repairs, and thefts.
     */
    public void simulateOperations() {
        Random random = new Random();

        for (Station station : stations) {
            List<Vehicle> rentalVehicles = controlCenter.getRentalVehicles();
            Vehicle vehicle;
            List<Vehicle> availableVehicles = station.getAvailableVehicles();

            // Simulate renting a vehicle with a random chance.
            if (random.nextDouble() < 0.50 && !availableVehicles.isEmpty()) {
                User user = getRandomUser();
                vehicle = availableVehicles.get(random.nextInt(availableVehicles.size()));
                rentVehicle(user, vehicle, station);
            }

            // Simulate returning a rented vehicle with a random chance.
            if (random.nextDouble() <  Math.min(Math.max((0.1 + 0.035 * rentalVehicles.size()), 0.0), 1.0) && !rentalVehicles.isEmpty()) {
                vehicle = rentalVehicles.get(random.nextInt(rentalVehicles.size()));
                returnVehicle(vehicle, station);
            }

            // Simulate the passage of time for rented vehicles, checking for theft conditions.
            for (Vehicle rentalVehicle : rentalVehicles) {
                if (rentalVehicle.getRentalTime() > 100) {
                    // Vehicle has been rented for a long time and is considered stolen.
                    System.out.println("Vehicle " + rentalVehicle.getId() + " has been stolen!");
                    rentalVehicle.accept(thief);
                } else {
                    // Increment the rental time for the vehicle.
                    rentalVehicle.incrementRentalTime();
                }
            }
        }
        // Initiate repairs for broken vehicles and display the current simulation state.
        repair();
        displaySimulationState();
    }
    
    /**
     * Initiates repairs for broken vehicles.
     */
    public void repair() {
        List<Vehicle> brokenVehicles = controlCenter.getBrokenVehicles();
        for (Vehicle vehicle : brokenVehicles) {
            vehicle.accept(repairer);
            vehicle.setUsageCount(0);
            /* if (vehicle.getState() == VehicleState.BROKEN) {
                vehicle.setState(VehicleState.AVAILABLE);
            } */
        }
    }


  

    /**
     * Rents a vehicle to a user from a station.
     *
     * @param user    The user renting the vehicle.
     * @param vehicle The vehicle being rented.
     * @param station The station from which the vehicle is rented.
     */
    public void rentVehicle(User user, Vehicle vehicle, Station station) {
        double rentalCost = vehicle.getCost();

        if (user.getBalance() >= rentalCost) {
            user.debitBalance(rentalCost);
            vehicle.start(user.getId()); 
            controlCenter.addToRentalVehicle(vehicle);
            station.removeVehicle(vehicle);
            vehicle.incrementRentalTime();
            System.out.println("User " + user.getId() + " rented Vehicle " + vehicle.getId() + " with " + vehicle.getCost() +
                    "$. Remaining balance: " + user.getBalance());
        } else {
            System.out.println("User " + user.getId() + " has insufficient funds. Unable to rent the vehicle.");
        }
    }

    /**
     * Returns a rented vehicle to a station.
     *
     * @param vehicle The vehicle being returned.
     * @param station The station to which the vehicle is returned.
     */
    public void returnVehicle(Vehicle vehicle, Station station) {
        User user = findUserByID(vehicle.getCurrentUserID());
        station.addVehicle(vehicle);
        controlCenter.removeFromRentalVehicle(vehicle);
        vehicle.resetRentalTime();
        vehicle.stop();
        if (vehicle.getState() == VehicleState.BROKEN) {
            controlCenter.addToBrokenVehicle(vehicle);
        }

        System.out.println("User " + user.getId() + " returned Vehicle " + vehicle.getId());
        
    }

    /**
     * Retrieves a random user from the list of users.
     *
     * @return A randomly selected user.
     */
    public User getRandomUser() {
        if (!users.isEmpty()) {
            return users.get(new Random().nextInt(users.size()));
        } else {
            return null;
        }
    }

    /**
     * Finds a user in the list of users based on the user ID.
     *
     * @param userID The ID of the user to find.
     * @return The user with the specified ID, or null if not found.
     */
    public User findUserByID(int userID) {
        for (User user : users) {
            if (user.getId() == userID) {
                return user;
            }
        }
        return null;
    }

    /**
     * Displays the current state of the simulation, including station information.
     */
    public void displaySimulationState() {
        SimulationDisplay.displayStations(controlCenter);
        System.out.println("---------------");
    }

    /**
     * Stops the simulation by shutting down the scheduler.
     */
    public void stopSimulation() {
        scheduler.shutdown();
    }
}
