package fil.l3.coo.Visitor;

import fil.l3.coo.Vehicle.Bike;
import fil.l3.coo.Vehicle.Scooter;

// An interface for implementing the Visitor pattern.
public interface Visitor {

    /**
     * Visits a bike.
     *
     * @param bike The bike to be visited.
     */
    void visit(Bike bike);

    /**
     * Visits a scooter.
     *
     * @param scooter The scooter to be visited.
     */
    void visit(Scooter scooter);
}
