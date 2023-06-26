package it.polimi.ingsw.exceptions;

/**
 * Thrown when a move is estimated as not valid in the model, reasons can be many
 */
public class InvalidMoveException extends Exception{
    public InvalidMoveException(String message){
        super(message);
    }
}
