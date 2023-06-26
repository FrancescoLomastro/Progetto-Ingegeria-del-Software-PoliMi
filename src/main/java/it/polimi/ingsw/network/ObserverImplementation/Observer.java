package it.polimi.ingsw.network.ObserverImplementation;

/**
 * Interface used to implements Observer class objects
 */
public interface Observer<SubjectType extends Observable<Event>, Event> {
    void update(SubjectType o, Event arg);
}
