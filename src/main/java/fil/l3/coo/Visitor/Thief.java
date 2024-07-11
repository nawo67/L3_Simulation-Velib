package fil.l3.coo.Visitor;

import fil.l3.coo.ControlCenter.ControlCenter;
import fil.l3.coo.Vehicle.*;

// Represents a visitor responsible for stealing bikes and scooters
public class Thief implements Visitor {
    private ControlCenter controlCenter; // The control center associated with the thief.
    
    /**
     * Constructs a new Thief object with the specified control center.
     * 
     * @param controlCenter The control center to be associated with the thief.
     */
    public Thief(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
    }

    /**
     * Visits a bike for theft, removes it from all vehicles, and sets its state to stolen.
     * 
     * @param bike The bike to be stolen.
     */
    @Override
    public void visit(Bike bike) {
        System.out.println("theft " + bike.getId());
        controlCenter.removeFromAllVehicles(bike);
        controlCenter.addToStolenVehicles(bike);
        bike.setState(VehicleState.STOLEN);
    }

    /**
     * Visits a scooter for theft, removes it from all vehicles, and sets its state to stolen.
     * 
     * @param scooter The scooter to be stolen.
     */
    @Override
    public void visit(Scooter scooter) {
        System.out.println("theft " + scooter.getId());
        controlCenter.removeFromAllVehicles(scooter);
        controlCenter.addToStolenVehicles(scooter);
        scooter.setState(VehicleState.STOLEN);
    }
}