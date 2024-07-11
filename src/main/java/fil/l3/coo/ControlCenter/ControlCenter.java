package fil.l3.coo.ControlCenter;

import fil.l3.coo.Station.Station;
import fil.l3.coo.Redistribution.*;
import fil.l3.coo.Vehicle.*;
import fil.l3.coo.Vehicle.VehicleDecorator.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a control center that manages stations in a free service bike's system.
public class ControlCenter implements Observer {
    private int vehicleDurability; // Default durability of managed vehicles.
    private int id; // Unique identifier of the control center.
    private static int nextId = 1; // Next available identifier for a control center.
    private ArrayList<Station> observedStations; // List of observed stations.
    private List<Vehicle> rentalVehicles = new ArrayList<>(); // List of available vehicles.
    private List<Vehicle> brokenVehicles = new ArrayList<>(); // List of broken vehicles.
    private List<Vehicle> stolenVehicles = new ArrayList<>(); // List of stolen vehicles.
    private Redistribution redistribution; // Redistribution strategy.
    private boolean isRedistributing; // Flag for ongoing redistribution.

    /**
     * Constructs a ControlCenter.
     */
    public ControlCenter() {
        this.id = nextId++;
        this.observedStations = new ArrayList<>();
        this.redistribution = new ClassicalRedistribution();
        this.vehicleDurability = 3;
        this.isRedistributing = false;
    }

    /**
     * Initializes the stations by adding vehicles to each observed station based on a specified rate.
     *
     * @param rate The rate used to calculate the number of bikes to be added, as a percentage of station capacity.
     */
    public void initStations(double rate) {
        this.isRedistributing=true;
        Random random = new Random();
        for (Station station : observedStations) {
            int nbVehicles = (int) (station.getCapacity() * rate);
            for (int i = 0; i < nbVehicles; i++) {
                if(random.nextBoolean()) {
                    Bike bike = new Bike(3, vehicleDurability);
                    int r = random.nextInt(3);
                    Bike bikeFinal;
                    if(r == 0){
                        bikeFinal = new BikeWithBasket(bike);
                    } else if (r==1) {
                        bikeFinal = new BikeWithLuggageCarrier(bike);
                    } else if (r==2) {
                        bikeFinal = new ElectricDecorator(bike);
                    } else {
                        bikeFinal = bike;
                    } 
                    station.addVehicle(bikeFinal);
                    System.out.println(bikeFinal.getDescription());
                } else {
                    Scooter scooter = new Scooter(4, vehicleDurability);
                    int r = random.nextInt(2);
                    Scooter scooterFinal;
                    if(r == 0){
                        scooterFinal = new ScooterWithPhoneHolder(scooter);
                    } else if (r==1) {
                        scooterFinal = new ScooterWithHeadlight(scooter);
                    } else {
                        scooterFinal = scooter;
                    } 
                    station.addVehicle(scooterFinal);
                    System.out.println(scooterFinal.getDescription());
                }

            }
        }
        this.isRedistributing=false;
    }

    /**
     * Gets the list of stolen vehicles.
     *
     * @return The list of available vehicles.
     */
    public List<Vehicle> getStolenVehicles() {
        return stolenVehicles;
    }

    /**
     * Adds stolen vehicles.
     *
     * @param vehicle The stolen vehicle.
     */
    public void addToStolenVehicles(Vehicle vehicle){
        this.stolenVehicles.add(vehicle);
    }

    /**
     * Gets the id of the control center.
     *
     * @return The id of the control center.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the list of available vehicles in all observed stations.
     *
     * @return The list of available vehicles.
     */
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Station station : observedStations) {
            availableVehicles.addAll(station.getAvailableVehicles());
        }
        return availableVehicles;
    }

    /**
     * Gets the list of broken vehicles.
     *
     * @return The list of broken vehicles.
     */
    public List<Vehicle> getBrokenVehicles() {
        return this.brokenVehicles;
    }

    /**
     * Gets the list of rental vehicles.
     *
     * @return The list of rental vehicles.
     */
    public List<Vehicle> getRentalVehicles() {
        return this.rentalVehicles;
    }

    /**
     * Sets a new redistribution strategy for the control center.
     *
     * @param newRedistribution The new redistribution strategy.
     */
    public void setRedistributionStrategy(Redistribution newRedistribution) {
        this.redistribution = newRedistribution;
    }
    
    /**
     * Indique si une redistribution est en cours.
     *
     * @return true si une redistribution est en cours, sinon false.
     */
    public boolean isRedistributing() {
        return isRedistributing;
    }

    /**
     * Définit l'état de la redistribution.
     *
     * @param redistributing true si une redistribution est en cours, sinon false.
     */
    public void setRedistributing(boolean redistributing) {
        isRedistributing = redistributing;
    }

    /**
     * Operate the specific redistribution.
     */
    public void redistribute() {
        isRedistributing = true;
        redistribution.operate(this.observedStations);
        isRedistributing = false;
    }

    /**
     * Adds an observed station.
     *
     * @param station The station to be observed.
     */
    public void addObservedStation(Station station) {
        observedStations.add(station);
        station.addObserver(this);
    }

    /**
     * Adds a vehicle to the list of rental vehicles.
     *
     * @param vehicle The vehicle to be added to rentals.
     */
    public void addToRentalVehicle(Vehicle vehicle) {
        rentalVehicles.add(vehicle);
    }

    /**
     * Removes a vehicle from the list of rental vehicles.
     *
     * @param vehicle The vehicle to be removed from rentals.
     */
    public void removeFromRentalVehicle(Vehicle vehicle) {
        if (!rentalVehicles.isEmpty()) {
            rentalVehicles.remove(vehicle);
        }
    }

    /**
     * Adds a vehicle to the list of broken vehicles.
     *
     * @param vehicle The vehicle to be added to broken vehicles.
     */
    public void addToBrokenVehicle(Vehicle vehicle) {
        brokenVehicles.add(vehicle);
    }

    /**
     * Removes a vehicle from the list of broken vehicles.
     *
     * @param vehicle The vehicle to be removed from broken vehicles.
     */
    public void removeFromBrokenVehicle(Vehicle vehicle) {
        if (!brokenVehicles.isEmpty()) {
            brokenVehicles.remove(vehicle);
            System.out.println("Broken vehicle: " + vehicle.getDescription());
        } else {
            System.out.println("No more broken vehicles");
        }
    }

    /**
     * Removes a vehicle from all lists: observed stations, rental, and broken.
     *
     * @param vehicle The vehicle to be removed.
     */
    public void removeFromAllVehicles(Vehicle vehicle) {
        for (Station station : observedStations) {
            station.removeVehicle(vehicle);
        }
        rentalVehicles.remove(vehicle);
        brokenVehicles.remove(vehicle);
    }


    /**
     * Gets the list of observed stations.
     *
     * @return The list of observed stations                
     */
    public List<Station> getObservedStations() {
        return this.observedStations;
    }

    public void checkAndRedistribute(Station station){
        if (station.hasOnlyOneVehicle() || station.isFull()) {
            System.out.println("Redistribution");
            this.redistribute();
        }
    }

    /**
     * Updates the control center when notified of a change in an observed subject.
     */
    @Override
    public void update(Station station) {
        if(!isRedistributing()){
            checkAndRedistribute(station);
        }
    }
}
