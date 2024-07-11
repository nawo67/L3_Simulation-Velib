package fil.l3.coo;

import fil.l3.coo.ControlCenter.*;
import fil.l3.coo.Station.*;
import fil.l3.coo.Vehicle.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StationTest {

    @Test
    public void testAddVehicleToNonFullStation() {
        Station station = new Station(3);
        Vehicle mockVehicle = Mockito.mock(Vehicle.class);
        station.addVehicle(mockVehicle);

        assertTrue(station.getVehicles().contains(mockVehicle));
    }

    @Test
    public void testAddVehicleToFullStation() {
        Station station = new Station(1);
        Vehicle mockVehicle1 = Mockito.mock(Vehicle.class);
        Vehicle mockVehicle2 = Mockito.mock(Vehicle.class);
        station.addVehicle(mockVehicle1);
        station.addVehicle(mockVehicle2);

        assertTrue(station.isFull());
        assertFalse(station.getVehicles().contains(mockVehicle2));
    }

    @Test
    public void testRemoveVehicleFromNonEmptyStation() {
        Station station = new Station(3);
        Vehicle mockVehicle = Mockito.mock(Vehicle.class);
        station.addVehicle(mockVehicle);

        station.removeVehicle(mockVehicle);

        assertFalse(station.getVehicles().contains(mockVehicle));
    }

    @Test
    public void testRemoveVehicleFromEmptyStation() {
        Station station = new Station(3);
        Vehicle mockVehicle = Mockito.mock(Vehicle.class);

        station.removeVehicle(mockVehicle);

        assertTrue(station.isEmpty());
    }

    @Test
    public void testDropVehicleToNonFullStation() {
        Station station = new Station(3);
        Vehicle mockVehicle = Mockito.mock(Vehicle.class);
        station.dropVehicle(mockVehicle);

        assertTrue(station.getVehicles().contains(mockVehicle));
    }

    @Test
    public void testDropVehicleToFullStation() {
        Station station = new Station(1);
        Vehicle mockVehicle1 = Mockito.mock(Vehicle.class);
        Vehicle mockVehicle2 = Mockito.mock(Vehicle.class);
        station.addVehicle(mockVehicle1);
        station.dropVehicle(mockVehicle2);

        assertFalse(station.getVehicles().contains(mockVehicle2));
    }

    @Test
    public void testObserversNotifiedOnAddRemove() {
        Station station = new Station(2);
        Observer mockObserver = Mockito.mock(Observer.class);
        station.addObserver(mockObserver);

        Vehicle mockVehicle = Mockito.mock(Vehicle.class);
        station.addVehicle(mockVehicle);
        Mockito.verify(mockObserver, Mockito.times(1)).update(station);

        station.removeVehicle(mockVehicle);
        Mockito.verify(mockObserver, Mockito.times(2)).update(station);
    }

    @Test
    public void testObserversNotNotifiedOnFullStation() {
        Station station = new Station(1);
        Observer mockObserver = Mockito.mock(Observer.class);
        station.addObserver(mockObserver);

        Vehicle mockVehicle1 = Mockito.mock(Vehicle.class);
        Vehicle mockVehicle2 = Mockito.mock(Vehicle.class);
        station.addVehicle(mockVehicle1);
        station.addVehicle(mockVehicle2);

        Mockito.verify(mockObserver, Mockito.times(1)).update(station);
    }
}