package it.polimi.ingsw.Network.Messages;


import it.polimi.ingsw.model.Cards.ObjectCard;

public class MessageLibrary extends MessageGame{
    ObjectCard[][] library;

    String player;

    public MessageLibrary() {
        super(MessageType.UPDATE_LIBRARY_MESSAGE);
    }

    public MessageLibrary(ObjectCard[][]library, String player){
        super(MessageType.UPDATE_LIBRARY_MESSAGE);
        this.library=library;
        this.player=player;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public ObjectCard[][] getLibrary() {
        return library;
    }

    public void setLibrary(ObjectCard[][] library) {
        this.library = library;
    }
}
