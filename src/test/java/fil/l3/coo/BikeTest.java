package fil.l3.coo;

import fil.l3.coo.Vehicle.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BikeTest {

    @Test
    public void testBikeGetId() {
        Bike bike = new Bike(15.0, 4);
        bike.setId(999);
        assertEquals(999, bike.getId());
    }

    @Test
    public void testBikeGetCost() {
        Bike bike = new Bike(15.0, 4);
        assertEquals(15.0, bike.getCost());
    }

    @Test
    public void testBikeGetDurability() {
        Bike bike = new Bike(15.0, 4);
        assertEquals(4, bike.getDurability());
    }

    @Test
    public void testBikeGetStateInitiallyAvailable() {
        Bike bike = new Bike(15.0, 4);
        assertEquals(VehicleState.AVAILABLE, bike.getState());
    }

    @Test
    public void testBikeIncrementUsage() {
        Bike bike = new Bike(15.0, 4);
        bike.incrementUsage();
        assertEquals(1, bike.getUsageCount());
    }

    @Test
    public void testBikeStartChangesStateToRental() {
        Bike bike = new Bike(15.0, 4);
        bike.start(123);
        assertEquals(VehicleState.RENTAL, bike.getState());
    }

    @Test
    public void testBikeStopChangesStateToAvailable() {
        Bike bike = new Bike(15.0, 4);
        bike.start(123);
        bike.stop();
        assertEquals(VehicleState.AVAILABLE, bike.getState());
    }

    @Test
    public void testBikeStopIncrementsUsageCount() {
        Bike bike = new Bike(15.0, 4);
        bike.start(123);
        bike.stop();
        assertEquals(1, bike.getUsageCount());
    }

    @Test
    public void testBikeResetRentalTimeSetsRentalTimeToZero() {
        Bike bike = new Bike(15.0, 4);
        bike.incrementRentalTime();
        bike.resetRentalTime();
        assertEquals(0, bike.getRentalTime());
    }

    @Test
    public void testBikeIncrementRentalTimeIncreasesRentalTime() {
        Bike bike = new Bike(15.0, 4);
        bike.incrementRentalTime();
        assertEquals(1, bike.getRentalTime());
    }

    @Test
    public void testBikeStartSetsCurrentUserID() {
        Bike bike = new Bike(15.0, 4);
        bike.start(123);
        assertEquals(123, bike.getCurrentUserID());
    }

    @Test
    public void testBikeStopResetsCurrentUserID() {
        Bike bike = new Bike(15.0, 4);
        bike.start(123);
        bike.stop();
        assertEquals(-1, bike.getCurrentUserID());
    }

    @Test
    public void testBikeIncrementUsageSetsStateToBrokenWhenDurabilityExceeded() {
        Bike bike = new Bike(15.0, 4);
        for (int i = 0; i < 5; i++) {
            bike.incrementUsage();
        }
        assertEquals(VehicleState.BROKEN, bike.getState());
    }

    @Test
    public void testBikeIncrementUsageDoesNotSetStateToBrokenIfDurabilityNotExceeded() {
        Bike bike = new Bike(15.0, 4);
        for (int i = 0; i < 3; i++) {
            bike.incrementUsage();
        }
        assertNotEquals(VehicleState.BROKEN, bike.getState());
    }
    
}