package fil.l3.coo.Vehicle;

import fil.l3.coo.Visitor.Visitor;

// The interface represents an object that can accept a visitor.
public interface Visitable {

    /**
     * Accepts a visitor, allowing it to perform operations on the object.
     * 
     * @param visitor The visitor to be accepted.
     */
    void accept(Visitor visitor);
}