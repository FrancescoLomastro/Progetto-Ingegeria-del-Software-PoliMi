package it.polimi.ingsw.Network.Client;


import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageLibrary;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.View.CLI;
import it.polimi.ingsw.model.Cards.ObjectCard;

public class ThreadOutputClient implements Observer<ClientObject, Message> {
    private final CLI cli;
    public ThreadOutputClient(CLI client){
        this.cli=client;
    }


    public void showGrid(){
        ObjectCard[][] grid = cli.getClientObject().getGrid();
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                System.out.print(
                        "| "+grid[i][j]+" "
                );
            }
            System.out.println("");
        }
    }

    public void showLibrary(MessageLibrary message){
        ObjectCard[][] library = cli.getClientObject().getLibrary(message.getPlayer());
        for(int i=0; i<library.length; i++){
            for(int j=0; j<library[i].length; j++){
                System.out.print(
                        "| "+library[i][j]+" "
                );
            }
            System.out.println("");
        }
    }

    public void initGame (){
        System.out.println("The game is started");
    }

    public void printAString(String s){
        System.out.println(s);
    }

    @Override
    public void update(ClientObject o, Message arg) {
        switch (arg.getType()){
            case UPDATE_GRID_MESSAGE -> showGrid();
            case UPDATE_LIBRARY_MESSAGE -> showLibrary( (MessageLibrary) arg);
        }
    }
}
