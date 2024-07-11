package fil.l3.coo;

import fil.l3.coo.Station.*;
import fil.l3.coo.Vehicle.*;
import fil.l3.coo.Redistribution.*;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class RedistributionTest {

    @Test
    public void testClassicalRedistributionOperate() {
        ClassicalRedistribution classicalRedistribution = new ClassicalRedistribution();

        ArrayList<Station> stations = new ArrayList<>();
        Station station1 = Mockito.mock(Station.class);
        Station station2 = Mockito.mock(Station.class);
        stations.add(station1);
        stations.add(station2);

        Vehicle vehicle1 = Mockito.mock(Vehicle.class);
        Vehicle vehicle2 = Mockito.mock(Vehicle.class);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        Mockito.when(station1.getVehicles()).thenReturn(vehicles);
        Mockito.when(station2.getVehicles()).thenReturn(new ArrayList<>());

        classicalRedistribution.operate(stations);

        Mockito.verify(station1, Mockito.times(1)).removeVehicle(vehicle1);
        Mockito.verify(station2, Mockito.times(1)).addVehicle(vehicle1);
        Mockito.verify(station1, Mockito.times(1)).removeVehicle(vehicle2);
        Mockito.verify(station2, Mockito.times(1)).addVehicle(vehicle2);
    }

    @Test
    public void testRandomRedistributionOperate() {
        RandomRedistribution randomRedistribution = new RandomRedistribution();

        ArrayList<Station> stations = new ArrayList<>();
        Station station1 = Mockito.mock(Station.class);
        Station station2 = Mockito.mock(Station.class);
        stations.add(station1);
        stations.add(station2);

        Vehicle vehicle1 = Mockito.mock(Vehicle.class);
        Vehicle vehicle2 = Mockito.mock(Vehicle.class);
        randomRedistribution.addWaitingVehicles(vehicle1);
        randomRedistribution.addWaitingVehicles(vehicle2);

        randomRedistribution.operate(stations);

        Mockito.verify(station1, Mockito.times(1)).addVehicle(vehicle1);
        Mockito.verify(station2, Mockito.times(1)).addVehicle(vehicle2);
    }

    @Test
    public void testEmptyTheStations() {
        ClassicalRedistribution classicalRedistribution = new ClassicalRedistribution();

        ArrayList<Station> stations = new ArrayList<>();
        Station station1 = new Station(4);
        Station station2 = new Station(5);
        stations.add(station1);
        stations.add(station2);

        Vehicle vehicle1 = new Bike(10.0, 5);
        Vehicle vehicle2 = new Scooter(15.0, 8);
        station1.addVehicle(vehicle1);
        station2.addVehicle(vehicle2);

        classicalRedistribution.emptyTheStations(stations);

        assertTrue(station1.getVehicles().isEmpty());
        assertTrue(station2.getVehicles().isEmpty());

        assertTrue(classicalRedistribution.getWaitingVehicles().contains(vehicle1));
        assertTrue(classicalRedistribution.getWaitingVehicles().contains(vehicle2));
    }
}