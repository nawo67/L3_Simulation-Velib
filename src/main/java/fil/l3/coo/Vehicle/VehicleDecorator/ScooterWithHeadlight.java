package fil.l3.coo.Vehicle.VehicleDecorator;

import fil.l3.coo.Vehicle.Scooter;

// Represents a scooter with an additional headlight.
public class ScooterWithHeadlight extends Scooter {
    
    private static int supplement = 1; // Cost supplement for the headlight.

    /**
     * Constructs a new ScooterWithHeadlight object based on the given Scooter.
     *
     * @param scooter The original scooter without the headlight.
     */
    public ScooterWithHeadlight(Scooter scooter) {
        super(scooter.getCost() + supplement, scooter.getDurability());
        this.setId(scooter.getId());
    }

    /**
     * Returns the description of the scooter with the headlight.
     *
     * @return A string describing the scooter with the headlight.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " and has a headlight";
    }
}
