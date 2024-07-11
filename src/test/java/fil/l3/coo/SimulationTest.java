package fil.l3.coo;

import fil.l3.coo.ControlCenter.*;
import fil.l3.coo.Station.*;
import fil.l3.coo.Vehicle.*;
import fil.l3.coo.User.User;
import fil.l3.coo.Simulation.GlobalSimulation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationTest {

    @Test
    public void testRentVehicleSufficientFunds() {
        ControlCenter controlCenter = new ControlCenter();
        User user = new User(50.0);
        List<User> users = new ArrayList<>();
        users.add(user);
        GlobalSimulation simulation = new GlobalSimulation(controlCenter, users);

        Station station = new Station(5);
        Bike availableBike = new Bike(10.0, 5);
        station.addVehicle(availableBike);
        controlCenter.addObservedStation(station);

        simulation.rentVehicle(user, availableBike, station);

        assertEquals(VehicleState.RENTAL, availableBike.getState());
        assertEquals(40.0, user.getBalance(), 0.001);
        assertFalse(station.getAvailableVehicles().contains(availableBike));
        assertTrue(controlCenter.getRentalVehicles().contains(availableBike));
    }

    @Test
    public void testRentVehicleInsufficientFunds() {
        ControlCenter controlCenter = new ControlCenter();
        User user = new User(10.0);
        List<User> users = new ArrayList<>();
        users.add(user);
        GlobalSimulation simulation = new GlobalSimulation(controlCenter, users);

        Station station = new Station(6);
        Bike availableBike = new Bike(20.0, 5);
        station.addVehicle(availableBike);
        controlCenter.addObservedStation(station);

        simulation.rentVehicle(user, availableBike, station);

        assertEquals(VehicleState.AVAILABLE, availableBike.getState());
        assertEquals(10.0, user.getBalance(), 0.001);
        assertTrue(station.getAvailableVehicles().contains(availableBike));
        assertFalse(controlCenter.getRentalVehicles().contains(availableBike));
    }

    @Test
    public void testReturnVehicle() {
        ControlCenter controlCenter = new ControlCenter();
        User user = new User(50.0);
        List<User> users = new ArrayList<>();
        users.add(user);
        GlobalSimulation simulation = new GlobalSimulation(controlCenter, users);

        Station station = new Station(4);
        Bike rentedBike = new Bike(10.0, 5);
        rentedBike.start(user.getId());
        controlCenter.addToRentalVehicle(rentedBike);
        station.addVehicle(rentedBike);
        controlCenter.addObservedStation(station);

        simulation.returnVehicle(rentedBike, station);

        assertEquals(VehicleState.AVAILABLE, rentedBike.getState());
        assertEquals(50.0, user.getBalance(), 0.001);
        assertFalse(controlCenter.getRentalVehicles().contains(rentedBike));
        assertTrue(station.getAvailableVehicles().contains(rentedBike));
    }

    @Test
    public void testRepair() {
        ControlCenter controlCenter = new ControlCenter();
        GlobalSimulation simulation = new GlobalSimulation(controlCenter, new ArrayList<>());

        Station station = new Station(5);
        Bike brokenBike = new Bike(10.0, 5);
        brokenBike.setState(VehicleState.BROKEN);
        controlCenter.addToBrokenVehicle(brokenBike);
        station.addVehicle(brokenBike);
        controlCenter.addObservedStation(station);

        simulation.repair();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(VehicleState.AVAILABLE, brokenBike.getState());
        assertEquals(0, brokenBike.getUsageCount());
        assertFalse(controlCenter.getBrokenVehicles().contains(brokenBike));
    }


    


    @Test
    public void testSimulationStartAndStop() {
        ControlCenter controlCenter = new ControlCenter();
        User user1 = new User(50.0);
        User user2 = new User(30.0);
        User user3 = new User(20.0);

        Station station1 = new Station(6);
        Station station2 = new Station(8);
        controlCenter.addObservedStation(station1);
        controlCenter.addObservedStation(station2);

        station1.addVehicle(new Bike(10.0, 5));
        station1.addVehicle(new Scooter(15.0, 8));
        station2.addVehicle(new Bike(12.0, 6));
        station2.addVehicle(new Scooter(18.0, 9));

        GlobalSimulation simulation = new GlobalSimulation(controlCenter, List.of(user1, user2, user3));

        simulation.startSimulation();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simulation.stopSimulation();

        assertTrue(true);
    }
}