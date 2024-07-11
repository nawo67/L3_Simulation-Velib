package fil.l3.coo.Vehicle.VehicleDecorator;

import fil.l3.coo.Vehicle.Bike;

// Represents a bike with an additional basket.
public class BikeWithBasket extends Bike {
    private static int supplement = 1; // Cost supplement for the basket.
    /**
     * Constructs a new BikeWithBasket object based on the given Bike.
     * 
     * @param bike The original bike without the basket.
     */
    public BikeWithBasket(Bike bike) {
        super(bike.getCost() + supplement, bike.getDurability());
        this.setId(bike.getId());
    }

    /**
     * Returns the description of the bike with the basket.
     * 
     * @return A string describing the bike with the basket.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " and have a basket";
    }
}