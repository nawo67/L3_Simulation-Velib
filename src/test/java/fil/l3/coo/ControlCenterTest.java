package fil.l3.coo;

import fil.l3.coo.ControlCenter.*;
import fil.l3.coo.Station.Station;
import fil.l3.coo.Vehicle.Vehicle;
import fil.l3.coo.Redistribution.Redistribution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.mockito.Mockito;

public class ControlCenterTest {

    @Test
    public void testInitStations() {
        ControlCenter controlCenter = new ControlCenter();
        ControlCenter spyControlCenter = Mockito.spy(controlCenter);
        Station mockStation = Mockito.mock(Station.class);
        spyControlCenter.addObservedStation(mockStation);

        spyControlCenter.initStations(0.5);

        Mockito.verify(spyControlCenter, Mockito.times(1)).initStations(0.5);
    }

    


    @Test
    public void testAddToRentalVehicle() {
        ControlCenter controlCenter = new ControlCenter();
        Vehicle mockVehicle = Mockito.mock(Vehicle.class);

        controlCenter.addToRentalVehicle(mockVehicle);

        assertTrue(controlCenter.getRentalVehicles().contains(mockVehicle));
    }

    @Test
    public void testRemoveFromRentalVehicle() {
        ControlCenter controlCenter = new ControlCenter();
        Vehicle mockVehicle = Mockito.mock(Vehicle.class);
        controlCenter.addToRentalVehicle(mockVehicle);

        controlCenter.removeFromRentalVehicle(mockVehicle);

        assertFalse(controlCenter.getRentalVehicles().contains(mockVehicle));
    }

    @Test
    public void testAddToBrokenVehicle() {
        ControlCenter controlCenter = new ControlCenter();
        Vehicle mockVehicle = Mockito.mock(Vehicle.class);

        controlCenter.addToBrokenVehicle(mockVehicle);

        assertTrue(controlCenter.getBrokenVehicles().contains(mockVehicle));
    }

    @Test
    public void testRemoveFromBrokenVehicle() {
        ControlCenter controlCenter = new ControlCenter();
        Vehicle mockVehicle = Mockito.mock(Vehicle.class);
        controlCenter.addToBrokenVehicle(mockVehicle);

        controlCenter.removeFromBrokenVehicle(mockVehicle);

        assertFalse(controlCenter.getBrokenVehicles().contains(mockVehicle));
    }

    @Test
    public void testRedistribute() {
        ControlCenter controlCenter = new ControlCenter();
        Redistribution mockRedistribution = Mockito.mock(Redistribution.class);
        controlCenter.setRedistributionStrategy(mockRedistribution);

        controlCenter.redistribute();

        Mockito.verify(mockRedistribution, Mockito.times(1)).operate(Mockito.<ArrayList<Station>>any());    
    }

    @Test
    public void testAddObservedStation() {
        ControlCenter controlCenter = new ControlCenter();
        Station mockStation = Mockito.mock(Station.class);

        controlCenter.addObservedStation(mockStation);

        assertTrue(controlCenter.getObservedStations().contains(mockStation));
    }

    @Test
    public void testCheckAndRedistribute() {
        ControlCenter controlCenter = new ControlCenter();
        ControlCenter spyControlCenter = Mockito.spy(controlCenter);
    
        Station mockStation = Mockito.mock(Station.class);
        spyControlCenter.addObservedStation(mockStation);
    
        spyControlCenter.checkAndRedistribute(mockStation);
    
        Mockito.verify(spyControlCenter, Mockito.never()).redistribute();
    }    

    @Test
    public void testUpdate() {
        ControlCenter controlCenter = Mockito.spy(new ControlCenter());
        Station mockStation = Mockito.mock(Station.class);
        controlCenter.addObservedStation(mockStation);

        Mockito.when(controlCenter.isRedistributing()).thenReturn(false);

        Mockito.doNothing().when(controlCenter).addObservedStation(Mockito.any());

        controlCenter.update(mockStation);

        Mockito.verify(controlCenter, Mockito.times(1)).checkAndRedistribute(mockStation);
    }


}