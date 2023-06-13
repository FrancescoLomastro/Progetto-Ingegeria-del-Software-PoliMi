package it.polimi.ingsw.network.Messages;

public class ErrorMessage extends Message {
    private String string;
    public ErrorMessage(String s) {
        super("Server",MessageType.ERROR);
        this.string=s;
    }

    public String getString() {
        return string;
    }
}
