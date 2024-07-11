package fil.l3.coo;

import fil.l3.coo.Vehicle.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ScooterTest {

    @Test
    public void testScooterGetId() {
        Scooter scooter = new Scooter(20.0, 6);
        scooter.setId(999);
        assertEquals(999, scooter.getId());
    }

    @Test
    public void testScooterGetCost() {
        Scooter scooter = new Scooter(20.0, 6);
        assertEquals(20.0, scooter.getCost());
    }

    @Test
    public void testScooterGetDurability() {
        Scooter scooter = new Scooter(20.0, 6);
        assertEquals(6, scooter.getDurability());
    }

    @Test
    public void testScooterGetStateInitiallyAvailable() {
        Scooter scooter = new Scooter(20.0, 6);
        assertEquals(VehicleState.AVAILABLE, scooter.getState());
    }

    @Test
    public void testScooterIncrementUsage() {
        Scooter scooter = new Scooter(20.0, 6);
        scooter.incrementUsage();
        assertEquals(1, scooter.getUsageCount());
    }


    @Test
    public void testScooterStartChangesStateToRental() {
        Scooter scooter = new Scooter(20.0, 6);
        scooter.start(456);
        assertEquals(VehicleState.RENTAL, scooter.getState());
    }

    @Test
    public void testScooterStopChangesStateToAvailable() {
        Scooter scooter = new Scooter(20.0, 6);
        scooter.start(456);
        scooter.stop();
        assertEquals(VehicleState.AVAILABLE, scooter.getState());
    }

    @Test
    public void testScooterStopIncrementsUsageCount() {
        Scooter scooter = new Scooter(20.0, 6);
        scooter.start(456);
        scooter.stop();
        assertEquals(1, scooter.getUsageCount());
    }

    @Test
    public void testScooterResetRentalTimeSetsRentalTimeToZero() {
        Scooter scooter = new Scooter(20.0, 6);
        scooter.incrementRentalTime();
        scooter.resetRentalTime();
        assertEquals(0, scooter.getRentalTime());
    }

    @Test
    public void testScooterIncrementRentalTimeIncreasesRentalTime() {
        Scooter scooter = new Scooter(20.0, 6);
        scooter.incrementRentalTime();
        assertEquals(1, scooter.getRentalTime());
    }

    @Test
    public void testScooterStartSetsCurrentUserID() {
        Scooter scooter = new Scooter(20.0, 6);
        scooter.start(456);
        assertEquals(456, scooter.getCurrentUserID());
    }

    @Test
    public void testScooterStopResetsCurrentUserID() {
        Scooter scooter = new Scooter(20.0, 6);
        scooter.start(456);
        scooter.stop();
        assertEquals(-1, scooter.getCurrentUserID());
    }

    @Test
    public void testScooterIncrementUsageSetsStateToBrokenWhenDurabilityExceeded() {
        Scooter scooter = new Scooter(20.0, 6);
        for (int i = 0; i < 7; i++) {
            scooter.incrementUsage();
        }
        assertEquals(VehicleState.BROKEN, scooter.getState());
    }

    @Test
    public void testScooterIncrementUsageDoesNotSetStateToBrokenIfDurabilityNotExceeded() {
        Scooter scooter = new Scooter(20.0, 6);
        for (int i = 0; i < 4; i++) {
            scooter.incrementUsage();
        }
        assertNotEquals(VehicleState.BROKEN, scooter.getState());
    }
}