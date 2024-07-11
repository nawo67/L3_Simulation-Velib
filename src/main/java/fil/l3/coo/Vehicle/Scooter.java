package fil.l3.coo.Vehicle;

import fil.l3.coo.Visitor.Visitor;

// Represents a scooter, a type of vehicle.
public class Scooter extends Vehicle {

    /**
     * Constructs a Scooter with the specified cost and durability.
     *
     * @param cost      The cost of the scooter.
     * @param durability The durability of the scooter.
     */
    public Scooter(double cost, int durability) {
        super(cost, durability);
    }

    /**
     * Gets a description of the scooter.
     *
     * @return The description of the scooter.
     */
    @Override
    public String getDescription() {
        return "The scooter's id is: " + getId() + ", costs " + getCost() + " euros and has the state: " + getState();
    }

    /**
     * Accepts a visitors.
     *
     * @param visitor The visitor to accept.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
