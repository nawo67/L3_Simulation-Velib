package fil.l3.coo.Vehicle;

import fil.l3.coo.Visitor.Visitor;

// Abstract class representing a vehicle.
public abstract class Vehicle implements Visitable {
    private int durability; // The durability of the vehicle.
    private int id; // The identifier of the vehicle.
    private static int nextId = 1; // The next available identifier for a vehicle.
    private double cost; // The cost of the vehicle.
    private VehicleState state; // The state of the vehicle.
    private int usageCount; // The number of times the vehicle has been used.
    private int rentalTime; // The rental time of the vehicle.
    private int currentUserID; // The ID of the current user renting the vehicle (-1 if not rented).

    /**
     * Constructs a Vehicle with the specified cost and durability.
     *
     * @param cost      The cost of the vehicle.
     * @param durability The durability of the vehicle.
     */
    public Vehicle(double cost, int durability) {
        this.durability = durability;
        this.id = nextId++;
        this.cost = cost;
        this.state = VehicleState.AVAILABLE;
        this.usageCount = 0;
        this.rentalTime = 0;
        this.currentUserID = -1;
    }

    /**
     * Gets the id of the vehicle.
     *
     * @return The identifier of the vehicle.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the number of times the vehicle has been used.
     *
     * @return The usage count of the vehicle.
     */
    public int getUsageCount() {
        return this.usageCount;
    }

    /**
     * Gets the rental time of the vehicle.
     *
     * @return The rental time of the vehicle.
     */
    public int getRentalTime() {
        return this.rentalTime;
    }

    /**
     * Gets the current state of the vehicle.
     *
     * @return The state of the vehicle.
     */
    public VehicleState getState() {
        return this.state;
    }

    /**
     * Gets the cost of the vehicle.
     *
     * @return The cost of the vehicle.
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Gets the durability of the vehicle.
     *
     * @return The durability of the vehicle.
     */
    public int getDurability() {
        return this.durability;
    }

    /**
     * Sets the id of the vehicle.
     *
     * @param id The new identifier for the vehicle.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the state of the vehicle.
     *
     * @param state The new state for the vehicle.
     */
    public void setState(VehicleState state) {
        this.state = state;
    }

    /**
     * Sets the usage count of the vehicle.
     *
     * @param usageCount The new usage count for the vehicle.
     */
    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }

    /**
     * Increments the usage count of the vehicle and updates its state if it's considered BROKEN.
     */
    public void incrementUsage() {
        this.usageCount++;
        if (this.usageCount >= durability) {
            this.setState(VehicleState.BROKEN);
        }
    }

    /**
     * Increments the rental time of the vehicle.
     */
    public void incrementRentalTime() {
        this.rentalTime++;
    }

    /**
     * Resets the rental time of the vehicle to zero.
     */
    public void resetRentalTime() {
        this.rentalTime = 0;
    }

    /**
     * Gets the current user ID renting the vehicle.
     *
     * @return The ID of the current user renting the vehicle (-1 if not rented).
     */
    public int getCurrentUserID() {
        return currentUserID;
    }

    /**
     * Sets the current user ID renting the vehicle.
     *
     * @param currentUserID The ID of the current user renting the vehicle (-1 if not rented).
     */
    public void setCurrentUserID(int currentUserID) {
        this.currentUserID = currentUserID;
    }

    /**
     * Starts the vehicle, updating its state to RENTAL.
     *
     * @param userID The ID of the user renting the vehicle.
     */
    public void start(int userID) {
        this.setState(VehicleState.RENTAL);
        this.setCurrentUserID(userID);
    }

    /**
     * Stops the vehicle, updating its state to AVAILABLE and incrementing the usage count.
     */
    public void stop() {
        this.setState(VehicleState.AVAILABLE);
        this.incrementUsage();
        this.setCurrentUserID(-1);
    }

    /**
     * Accepts a visitor.
     *
     * @param visitor The visitor to accept.
     */
    @Override
    public abstract void accept(Visitor visitor);

    /**
     * Gets a description of the vehicle.
     *
     * @return The description of the vehicle.
     */
    public abstract String getDescription();
}
