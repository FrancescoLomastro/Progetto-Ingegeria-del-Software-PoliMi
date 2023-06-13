package it.polimi.ingsw.model.Cards;

/**
 * This interface is used to create an object that represents a method.
 * CustomizedFunction is a customization of the already existing interface Function<T,R>, without any parameter type.
 * @author Lo Mastro Francesco
 */
public interface CustomizedFunction<R> {
    R apply();
}
