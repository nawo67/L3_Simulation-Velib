package fil.l3.coo.Station;

import fil.l3.coo.ControlCenter.Observer;

// Represents an observable object that can be observed by observers.
public interface Observable {
    
    /**
     * Adds an observer to the list of observers.
     * @param observer The observer to be added.
     */
    void addObserver(Observer observer);

    /**
     * Removes an observer from the list of observers.
     * @param observer The observer to be removed.
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all registered observers about a change in the observable object.
     */
    void notifyObservers();
}