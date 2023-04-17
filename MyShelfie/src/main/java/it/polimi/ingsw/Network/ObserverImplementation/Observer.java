package it.polimi.ingsw.Network.ObserverImplementation;

public interface Observer<SubjectType extends Observable<Event>, Event> {


    /*l'unico evento che può generare la view è il fatto di mandare una mossa, quindi
    * in arg dovrei avere solo l'input della mossa. In teoria a questo punto dovrei
    * già essere collegato, quindi non mi importa di registrarmi.
    * Arg quindi sarà sicuramente un array o vettore di posizioni
    * In questo caso ho ricevuto una notifica dalla view, quindi devo andare ad aggiornare il
    * modello in qualche modo */
    void update(SubjectType o, Event arg);
}
