package fil.l3.coo.Vehicle.VehicleDecorator;

import fil.l3.coo.Vehicle.Bike;

// Represents a bike with an additional luggage carrier.
public class BikeWithLuggageCarrier extends Bike {
    private static int supplement = 2; // Cost supplement for the basket.
    /**
     * Constructs a new BikeWithLuggageCarrier object based on the given Bike.
     * 
     * @param bike The original bike without the luggage carrier.
     */
    public BikeWithLuggageCarrier(Bike bike) {
        super(bike.getCost() + supplement, bike.getDurability());
        this.setId(bike.getId());
    }

    /**
     * Returns the description of the bike with the luggage carrier.
     * 
     * @return A string describing the bike with the luggage carrier.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " and have a luggage carrier";
    }
}