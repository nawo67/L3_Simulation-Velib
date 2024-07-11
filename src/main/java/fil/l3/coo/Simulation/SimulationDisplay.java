package fil.l3.coo.Simulation;

import fil.l3.coo.Station.Station;
import fil.l3.coo.ControlCenter.ControlCenter;
import fil.l3.coo.Vehicle.*;

import java.util.List;

// Displays the current status of stations and the control center in the simulation.
public class SimulationDisplay {

    /**
     * Displays the information about stations and the control center.
     *
     * @param controlCenter The control center containing the observed stations.
     */
    public static void displayStations(ControlCenter controlCenter) {
        System.out.println("+----+-----------+-------+----+-----------------------------------------------------------------+");
        System.out.println("| ID | Capacity  |(🛴/🚲)| 🛠  |  Vehicles                                                       |");
        System.out.println("+----+-----------+-------+----+-----------------------------------------------------------------+");
        displayVehicles(controlCenter.getObservedStations());
        System.out.println("+----+------------------------------------------------------------------------------------------+");
        System.out.println("Control center n°" + controlCenter.getId() + " counts " + controlCenter.getRentalVehicles().size() + " rented vehicles 🔒");
        System.out.println("Control center n°" + controlCenter.getId() + " counts " + controlCenter.getStolenVehicles().size() + " stolen vehicles 🚨");
    }

    /**
     * Helper method to display the vehicles in a given list.
     *
     * @param stations The list of stations to display.
     */
    private static void displayVehicles(List<Station> stations) {
        for (Station station : stations) {
            String format = "| %-2d | %-9s | %-5s | %-2d | %-45s %n";
            System.out.format(format, station.getId(), station.getCapacity(), station.getAvailableVehicles().size(), station.getBrokenVehicles().size(), displayVehicles2(station.getVehicles(), station.getCapacity()));
        }
    }

    /**
     * Helper method to display the vehicles in a given list.
     *
     * @param vehicles The list of vehicles to display.
     * @param capacity The capacity of the station.
     * @return A formatted string representing the vehicles.
     */
    private static String displayVehicles2(List<Vehicle> vehicles, int capacity) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if (i < vehicles.size()) {
                result.append(getVehicleIcon(vehicles.get(i).getState(), vehicles.get(i))).append(" ");
            } else {
                result.append("❌ ");
            }
        }
        return result.toString();
    }

    /**
     * Helper method to get an icon based on the vehicle state.
     *
     * @param state   The state of the vehicle.
     * @param vehicle The vehicle.
     * @return An icon representing the vehicle state.
     */
    private static String getVehicleIcon(VehicleState state, Vehicle vehicle) {
        switch (state) {
            case AVAILABLE:
                return vehicle instanceof Scooter ? "🛴" : "🚲"; // Scooter icon for scooters, Bicycle icon for bikes
            case BROKEN:
                return "🛠";
            case RENTAL:
                return "🔒";
            case STOLEN:
                return "🚨";
            default:
                return " ";
        }
    }
}