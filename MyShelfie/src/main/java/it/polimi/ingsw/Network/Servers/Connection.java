package it.polimi.ingsw.Network.Servers;



import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.StatusNetwork;
import java.io.IOException;

public interface Connection {
    public void sendMessage(Message message) throws IOException;
    public StatusNetwork getStatusNetwork();
    public void setStatusNetwork(StatusNetwork statusNetwork);
    public String getPlayerName();
    public void setPlayerName(String playerName);
}
