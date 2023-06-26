package it.polimi.ingsw.enums;

/**
 * This class is used by the CLI to set the destination of the user input in the CLI.
 */
public enum InputStateCLI {

    /**
     * The input must go to the chat
     */
    CHAT,

    /**
     * The input must be used to answer a ClientController request
     */
    REQUEST;
}
