package fil.l3.coo;

import fil.l3.coo.Vehicle.*;
import fil.l3.coo.Vehicle.VehicleDecorator.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {

    @Test
    public void testBikeWithBasketDescriptionContainsBasket() {
        Bike bike = new Bike(15.0, 4);
        BikeWithBasket bikeWithBasket = new BikeWithBasket(bike);
        assertTrue(bikeWithBasket.getDescription().toLowerCase().contains("basket"));
        assertEquals(16.0, bikeWithBasket.getCost(), 0.001); // Vérifie que le coût a augmenté
    }

    @Test
    public void testBikeWithLuggageCarrierDescriptionContainsLuggageCarrier() {
        Bike bike = new Bike(15.0, 4);
        BikeWithLuggageCarrier bikeWithLuggageCarrier = new BikeWithLuggageCarrier(bike);
        assertTrue(bikeWithLuggageCarrier.getDescription().toLowerCase().contains("luggage carrier"));
        assertEquals(17.0, bikeWithLuggageCarrier.getCost(), 0.001); // Vérifie que le coût a augmenté
    }

    @Test
    public void testElectricDecoratorDescriptionContainsElectric() {
        Bike bike = new Bike(15.0, 4);
        ElectricDecorator electricDecorator = new ElectricDecorator(bike);
        assertTrue(electricDecorator.getDescription().toLowerCase().contains("electric"));
        assertEquals(18.0, electricDecorator.getCost(), 0.001); // Vérifie que le coût a augmenté
    }

    @Test
    public void testScooterWithHeadlightDescriptionContainsHeadlight() {
        Scooter scooter = new Scooter(20.0, 6);
        ScooterWithHeadlight scooterWithHeadlight = new ScooterWithHeadlight(scooter);
        assertTrue(scooterWithHeadlight.getDescription().toLowerCase().contains("headlight"));
        assertEquals(21.0, scooterWithHeadlight.getCost(), 0.001); // Vérifie que le coût a augmenté
    }

    @Test
    public void testScooterWithPhoneHolderDescriptionContainsPhoneHolder() {
        Scooter scooter = new Scooter(20.0, 6);
        ScooterWithPhoneHolder scooterWithPhoneHolder = new ScooterWithPhoneHolder(scooter);
        assertTrue(scooterWithPhoneHolder.getDescription().toLowerCase().contains("phone holder"));
        assertEquals(22.0, scooterWithPhoneHolder.getCost(), 0.001); // Vérifie que le coût a augmenté
    }
}