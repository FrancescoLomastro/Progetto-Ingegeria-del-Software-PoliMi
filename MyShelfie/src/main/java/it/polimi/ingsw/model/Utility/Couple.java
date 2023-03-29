package it.polimi.ingsw.model.Utility;

/*TODO non capisco perché couple ha due oggetti generici, tanto sappiamo esattamente che si andrà
*  sicuramente colore posizioni, tanto vale segnarle direttamente
*  In personalCard ho dovuto far un cast esplicito (non che crei problemi)*/
public class Couple<E1,E2>{
    private E1 first;
    private E2 second;

    public Couple(E1 first, E2 second) {
        this.first = first;
        this.second = second;
    }

    public E1 getFirst() {
        return first;
    }

    public E2 getSecond() {
        return second;
    }

    public void setFirst(E1 first) {
        this.first = first;
    }

    public void setSecond(E2 second) {
        this.second = second;
    }
    public void set(E1 first, E2 second) {
        this.first = first;
        this.second = second;
    }
    public boolean equalCoordinates(Integer x, Integer y)
    {

        return x.equals(this.first) && y.equals(this.second);
    }
}
