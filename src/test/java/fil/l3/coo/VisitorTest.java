package fil.l3.coo;

import fil.l3.coo.ControlCenter.ControlCenter;
import fil.l3.coo.Vehicle.*;
import fil.l3.coo.Visitor.Repairer;
import fil.l3.coo.Visitor.Thief;
import fil.l3.coo.Station.Station;

import org.junit.Test;
import static org.junit.Assert.*;

public class VisitorTest {

    @Test
    public void testRepairerVisitBike() throws InterruptedException {
        ControlCenter controlCenter = new ControlCenter();
        Bike brokenBike = new Bike(10.0, 5);
        brokenBike.setState(VehicleState.BROKEN);
        controlCenter.addToBrokenVehicle(brokenBike);

        Repairer repairer = new Repairer(controlCenter);

        repairer.visit(brokenBike);
        Thread.sleep(5000); 
        assertEquals(VehicleState.AVAILABLE, brokenBike.getState());
        assertEquals(0, brokenBike.getUsageCount());
        assertFalse(controlCenter.getBrokenVehicles().contains(brokenBike));
    }

    @Test
    public void testRepairerVisitScooter() throws InterruptedException {
        ControlCenter controlCenter = new ControlCenter();
        Scooter brokenScooter = new Scooter(10.0, 5);
        brokenScooter.setState(VehicleState.BROKEN);
        controlCenter.addToBrokenVehicle(brokenScooter);

        Repairer repairer = new Repairer(controlCenter);

        repairer.visit(brokenScooter);
        Thread.sleep(5000);
        assertEquals(VehicleState.AVAILABLE, brokenScooter.getState());
        assertEquals(0, brokenScooter.getUsageCount());
        assertFalse(controlCenter.getBrokenVehicles().contains(brokenScooter));
    }

    @Test
    public void testThiefVisitScooter() {
        ControlCenter controlCenter = new ControlCenter();
        Station station = new Station(5);
        controlCenter.addObservedStation(station);
        Scooter availableScooter = new Scooter(15.0, 8);
        station.addVehicle(availableScooter);

        Thief thief = new Thief(controlCenter);

        thief.visit(availableScooter);
        assertEquals(VehicleState.STOLEN, availableScooter.getState());
        assertFalse(station.getVehicles().contains(availableScooter));
        assertTrue(controlCenter.getStolenVehicles().contains(availableScooter));
    }

    @Test
    public void testThiefVisitBike() {
        ControlCenter controlCenter = new ControlCenter();
        Station station = new Station(5);
        controlCenter.addObservedStation(station);
        Bike availableBike = new Bike(15.0, 8);
        station.addVehicle(availableBike);

        Thief thief = new Thief(controlCenter);

        thief.visit(availableBike);
        assertEquals(VehicleState.STOLEN, availableBike.getState());
        assertFalse(station.getVehicles().contains(availableBike));
        assertTrue(controlCenter.getStolenVehicles().contains(availableBike));
    }
}