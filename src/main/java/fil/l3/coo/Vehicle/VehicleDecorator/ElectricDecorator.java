package fil.l3.coo.Vehicle.VehicleDecorator;

import fil.l3.coo.Vehicle.*;

// Represents a decorator for an electric bike.
public class ElectricDecorator extends Bike {
    private static int supplement = 3; // Cost supplement for the electric feature.

    /**
     * Constructs a new ElectricDecorator object based on the given Vehicle.
     *
     * @param vehicle The original vehicle to be decorated as an electric bike.
     */
    public ElectricDecorator(Vehicle vehicle) {
        super(vehicle.getCost() + supplement, vehicle.getDurability());
    }

    /**
     * Returns the description of the electric bike.
     *
     * @return A string describing the electric bike.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " and is electric";
    }
}
