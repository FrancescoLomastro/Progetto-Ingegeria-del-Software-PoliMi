package it.polimi.ingsw.Network.Messages;

public class AcceptedLoginMessage extends Message {
    private final String name;
    public AcceptedLoginMessage(String name){
        super("Server", MessageType.ACCEPTED_LOGIN_MESSAGE);
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
