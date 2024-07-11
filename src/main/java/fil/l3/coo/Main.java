package fil.l3.coo;

import fil.l3.coo.Station.Station;
import fil.l3.coo.ControlCenter.ControlCenter;
import fil.l3.coo.User.User;
import fil.l3.coo.Simulation.GlobalSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Main class serving as the entry point for the application.
public class Main {

    /**
     * The main method that is executed when the program is run.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Creating stations
        Station station1 = new Station(15);
        Station station2 = new Station(11);
        Station station3 = new Station(17);
        Station station4 = new Station(12);

        // Creating a control center and adding observed stations
        ControlCenter controlCenter = new ControlCenter();
        controlCenter.addObservedStation(station1);
        controlCenter.addObservedStation(station2);
        controlCenter.addObservedStation(station3);
        controlCenter.addObservedStation(station4);

        // Creating users
        List<User> users = createUsers(50);

        // Creating a global simulation and starting it
        GlobalSimulation simulation = new GlobalSimulation(controlCenter, users);
        controlCenter.initStations(0.75);
        simulation.startSimulation();
    }

    /**
     * Creates a list of users with random initial balances.
     *
     * @param numberOfUsers The number of users to create.
     * @return A list of User objects.
     */
    private static List<User> createUsers(int numberOfUsers) {
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= numberOfUsers; i++) {
            users.add(new User(random.nextInt(250)));
        }
        return users;
    }
}
