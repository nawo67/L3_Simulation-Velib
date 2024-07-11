package fil.l3.coo.Vehicle.VehicleDecorator;

import fil.l3.coo.Vehicle.Scooter;

// Represents a scooter with an additional phone holder.
public class ScooterWithPhoneHolder extends Scooter {
    
    private static int supplement = 2; // Cost supplement for the phone holder.

    /**
     * Constructs a new ScooterWithPhoneHolder object based on the given Scooter.
     *
     * @param scooter The original scooter without the phone holder.
     */
    public ScooterWithPhoneHolder(Scooter scooter) {
        super(scooter.getCost() + supplement, scooter.getDurability());
        this.setId(scooter.getId());
    }

    /**
     * Returns the description of the scooter with the phone holder.
     *
     * @return A string describing the scooter with the phone holder.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " and has a phone holder";
    }
}
