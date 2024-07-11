package fil.l3.coo.Vehicle;

import fil.l3.coo.Visitor.*;

// Represents a bike, a type of vehicle.
public class Bike extends Vehicle {

    /**
     * Constructs a Bike with the specified cost and durability.
     *
     * @param cost      The cost of the bike.
     * @param durability The durability of the bike.
     */
    public Bike(double cost, int durability) {
        super(cost, durability);
    }

    /**
     * Gets a description of the bike.
     *
     * @return The description of the bike.
     */
    @Override
    public String getDescription() {
        return "The bike's id is: " + getId() + ", costs " + getCost() + " euros and has the state: " + getState();
    }

    /**
     * Accepts a visitor.
     *
     * @param visitor The visitor to accept.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
