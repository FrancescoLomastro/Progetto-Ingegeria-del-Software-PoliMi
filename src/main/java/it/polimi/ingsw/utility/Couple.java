package it.polimi.ingsw.utility;

import java.io.Serializable;

/**
 * A generic class that includes two generic objects
 */
public class Couple<E1,E2> implements Serializable {
    private static final long serialVersionUID = 1L;

    private E1 first;
    private E2 second;

    public Couple(E1 first, E2 second) {
        this.first = first;
        this.second = second;
    }

    /**It returns the first general element
     * @return the first generic object in the couple
     * @author: Francesco Lo Mastro
     */
    public E1 getFirst() {
        return first;
    }

    /**It returns the second general element
     * @author: Francesco Lo Mastro
     * @return the second generic object in the couple
     */
    public E2 getSecond() {
        return second;
    }

    /**
     * Sets the first object in the couple
     * @author: Francesco Lo Mastro
     * @param first a generic object
     */
    public void setFirst(E1 first) {
        this.first = first;
    }

    /**
     * Sets the second object in the couple
     * @author: Francesco Lo Mastro
     * @param second a generic object
     */
    public void setSecond(E2 second) {
        this.second = second;
    }

}
