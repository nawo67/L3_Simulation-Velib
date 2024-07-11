package fil.l3.coo.Visitor;

import fil.l3.coo.ControlCenter.ControlCenter;
import fil.l3.coo.Vehicle.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Represents a visitor responsible for repairing bikes and scooters.
public class Repairer implements Visitor {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor(); // Executor service for handling repairs asynchronously.
    private ControlCenter controlCenter; // The control center associated with the repairer.

    /**
     * Constructs a new Repairer object with the specified control center.
     *
     * @param controlCenter The control center to be associated with the repairer.
     */
    public Repairer(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
    }

    /**
     * Visits a bike for repair.
     *
     * @param bike The bike to be repaired.
     */
    @Override
    public void visit(Bike bike) {
        executor.submit(() -> {
            try {
                if (controlCenter.getBrokenVehicles().contains(bike)) {
                    Thread.sleep(4000); // Simulating repair time.
                    System.out.println("Repair " + bike.getId());
                    bike.setState(VehicleState.AVAILABLE);
                    bike.setUsageCount(0);
                    controlCenter.removeFromBrokenVehicle(bike);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Visits a scooter for repair.
     *
     * @param scooter The scooter to be repaired.
     */
    @Override
    public void visit(Scooter scooter) {
        executor.submit(() -> {
            try {
                if (controlCenter.getBrokenVehicles().contains(scooter)) {
                    Thread.sleep(3000); // Simulating repair time.
                    System.out.println("Repair " + scooter.getId());
                    scooter.setState(VehicleState.AVAILABLE);
                    scooter.setUsageCount(0);
                    controlCenter.removeFromBrokenVehicle(scooter);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Shuts down the repairer's executor service.
     */
    public void shutdown() {
        executor.shutdown();
    }
}
