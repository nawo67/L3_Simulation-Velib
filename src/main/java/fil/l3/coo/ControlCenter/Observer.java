package fil.l3.coo.ControlCenter;

import fil.l3.coo.Station.Station;

// Observer interface for objects that need to be notified of updates.
public interface Observer {
    /**
     * Notifies the observer of an update.
     */
    void update(Station station);
}
