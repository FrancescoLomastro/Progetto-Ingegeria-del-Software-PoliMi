package it.polimi.ingsw.network.ObserverImplementation;

import java.util.Vector;


public class Observable<Event> {
    private boolean changed = false;
    private Vector<Observer<? extends Observable<Event>, Event>> obs;

    /**
     * Builds an observable object
     */
    public Observable() {
        obs = new Vector<>();
    }


    public synchronized void addObserver(Observer<? extends Observable<Event>, Event> o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }


    public void notifyObservers(Event arg) {

        Object[] arrLocal;

        synchronized (this) {

            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer<Observable<Event>, Event>)arrLocal[i]).update(this, arg);
    }

    protected synchronized void setChanged() {
        changed = true;
    }


    protected synchronized void clearChanged() {
        changed = false;
    }

}
