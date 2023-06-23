package it.polimi.ingsw.view.Cli;
import it.polimi.ingsw.network.Client.ClientModel;
import it.polimi.ingsw.network.Messages.*;
import it.polimi.ingsw.view.OBSMessages.*;
import it.polimi.ingsw.view.*;
import it.polimi.ingsw.exceptions.ResetMoveException;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.enums.ClientState;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.enums.Type;
import it.polimi.ingsw.utility.Couple;
import it.polimi.ingsw.utility.Position;
import it.polimi.ingsw.utility.PrinterUtils;


import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
/**This class handles input and output in cli version
 * @author: Riccardo Figini*/
public class Cli extends View implements Runnable {
    private final Scanner scanner;
    private boolean chatAvailable;
    private ClientState state;
    private String inputRequestBuffer;
    private final String divisor="\n|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";

    public Cli()
    {
        clientModel=new ClientModel();
        clientModel.addObserver(this);
        scanner = new Scanner(System.in);
        chatAvailable=false;
        state=ClientState.CHAT;
    }





    /**
     * Turns on a thread to handle user input for the CLI
     */
    @Override
    public void startView() {
        new Thread(this).start();
        printTitle();
        setChanged();
        notifyObservers(new OBS_OnlyTypeMessage(OBS_MessageType.START));
    }




    /**
     * Prints the game title
     */
    private void printTitle()
    {
        System.out.println("""
                  _________________     ________________________   _________________
                  |        ||  |  |     |     ||  |  |     ||  |   |     ||  |     |
                  |  _  _  ||  !  |     |  ___!|  !  |  ___!|  |   |  ___!|  |  ___!
                  |  |  |  |!_   _!     !__   ||     |  __|_|  !___|  __| |  |  __|_
                  |  |  |  | |   |      |     ||  |  |     ||     ||  |   |  |     |
                  !__!__!__! !___!      !_____!!__!__!_____!!_____!!__!   !__!_____!

                """);
    }


    /**
     * Asks the user for all the initial information required to connect to a server
     * notifies the controller with the information collected
     */
    @Override
    public void askInitialInfo()
    {
        String chosenUsername=askUsername();
        int chosenTechnology=askTechnology();
        String chosenAddress=askAddress();
        int chosenPort;
        if(chosenTechnology==0)
            chosenPort=askPort(defaultRMIPort);
        else
            chosenPort=askPort(defaultSocketPort);
        System.out.println("\n>> Connection to server...");

        OBS_Message msg = new OBS_InitialInfoMessage(chosenUsername,chosenTechnology,chosenAddress,chosenPort);
        setChanged();
        notifyObservers(msg);
    }


    /**
     * Asks the user for the number of player that will populate the lobby
     * the number must be between from the two parameters
     * @param min inferior limit
     * @param max superior limit
     */
    @Override
    public void askNumberOfPlayers(int min, int max)
    {
        String input;
        Boolean badInput;
        int value=0;
        do
        {
            System.out.println("\n>> You are the first player, please type the number of players,");
            System.out.print("[Number must be between " + min + " and " + max+"]: ");
            badInput=false;
            input = getInputRequest();
            try {
                value = Integer.parseInt(input);
                if(value<min || value> max)
                {
                    badInput=true;
                }
            }
            catch (NumberFormatException e)
            {
                badInput=true;
            }
            if(badInput)
            {
                System.out.println("ERROR >> You typed an invalid number, please retry\n");
            }
        }while(badInput);

        OBS_Message msg = new OBS_NumberOfPlayerMessage(value);
        setChanged();
        notifyObservers(msg);
    }


    /**
     * Asks a new username to by used by the user
     */
    @Override
    public void onInvalidUsername()
    {
        System.out.println(">> The typed username was already used, please type another username or try later");
        OBS_Message msg = new OBS_ChangedUsernameMessage(askUsername());

        setChanged();
        notifyObservers(msg);
    }

    @Override
    public void lobbyUpdate(String string) {

    }

    @Override
    public void acceptedLogin() {
        System.out.println("Connection accepted, waiting for other players");
    }
    /**
     * Message to be printed as the game starts
     */
    @Override
    public void startGame() {
        System.out.println("\nGame started");
    }


    /**
     * Prints the chat message
     * @param username  the username of the sender
     * @param text  the text of the mesage
     */
    @Override
    public void chatMessage(String username, String text) {
        if(username.equals(clientModel.getMyName()))
            username="You";
        System.out.println("\n["+username+"]\n  "+text);
    }


    /**Prints everything in client model
     * @author: Riccardo Figini
     * */
    @Override
    public void printAll() {
        showGrid(clientModel.getGrid(), MessageGrid.TypeOfGridMessage.NEED_TO_BE_PRINTED);
        System.out.println("\nFirst common goal: " + clientModel.getDescriptionFirstCommonGoal());
        System.out.println("\nSecond common goal: " + clientModel.getDescriptionSecondCommonGoal());
        printPersonalGoal(clientModel.getGoalList());
        Map<String, ObjectCard[][]> map = clientModel.getAllLibrary();
        for(Map.Entry<String, ObjectCard[][]> entry : map.entrySet() ){
            showLibrary(clientModel.getLibrary(entry.getKey()), entry.getKey(), null, null );
        }
        System.out.println("Points:\n");
        printPoints();
    }


    /** Asks user to perform his move
     * @author: Riccardo Figini*
     */
    @Override
    public void askMove()  {
        int column=0, numberOfCards;
        Position[] position=null;
        boolean reset;
        do
        {
            try
            {
                reset=false;
                System.out.println("How many card do you want? [minimum 1, max 3]");
                numberOfCards = getNumberWithLimit(3);
                position = askPositions(numberOfCards);
                System.out.println("In which column do you want insert this cards? [type \"RESET\" to cancel]");
                column = getNumberWithLimit(5) - 1;
            } catch (ResetMoveException e) {
                reset=true;
            }
        }while(reset);

        OBS_Message msg = new OBS_MoveMessage(position, column);
        setChanged();
        notifyObservers(msg);
    }


    /**
     * @author: Riccardo Figini
     * */
    @Override
    public void printPoints() {
        Map<String, Integer> map1 = clientModel.getPointsMap();
        for(Map.Entry<String, Integer> entry : map1.entrySet()){
            System.out.println("- "+entry.getKey()+": "+entry.getValue());
        }
    }

    /**
     * This method allows turns on chat messages input
     */
    @Override
    public void startChat() {
        chatAvailable=true;
    }


    /**Prints generic string
     * @author: Riccardo Figini
     * @param s String to print
     * */
    @Override
    public void printMessage(String s)
    {
        System.out.println("\n>> "+s);
    }
    @Override
    public void printMessage(String s, Message msg)
    {
        this.printMessage(s);
    }


    /**
     * Prints the grid passed as parameter
     * @author: Riccardo Figini
     * */
    @Override
    public void showGrid(ObjectCard[][] matrice, MessageGrid.TypeOfGridMessage typeOfGridMessage)
    {
        String action;
        if(typeOfGridMessage == MessageGrid.TypeOfGridMessage.UPDATE_AFTER_MOVE)
            action="is updated";
        else if(typeOfGridMessage == MessageGrid.TypeOfGridMessage.INIT)
            action="is initialized";
        else
            action="";
        String title="Living room grid " + action+ ":   ";
        String title_space= PrinterUtils.printEquivalentSpace(title);
        String top_header = "|   |";
        String valoreStringa;

        System.out.println(divisor);
        System.out.print(title_space);
        for(int colonna=1; colonna<=matrice.length;colonna++)
        {
            top_header+="| "+colonna+" |";
        }
        System.out.println(top_header);


        for (int riga = 0; riga < matrice.length; riga++) {
            if(riga==matrice.length/2)
                System.out.print(title+(riga+1) + " -> ");
            else
                System.out.print(title_space+(riga+1) + " -> ");
            for (int colonna = 0; colonna < matrice[0].length; colonna++) {

                if(matrice[riga][colonna]==null)
                {
                    valoreStringa = " ";
                }
                else
                    valoreStringa = ""+matrice[riga][colonna];
                System.out.print("| "+valoreStringa+" |");
            }
            System.out.println();
        }
    }


    /**
     * Show Library of specific player
     * @author: Riccardo Figini
     * @param library library to print
     * */
    @Override
    public void showLibrary(ObjectCard[][] library, String username, Position[] oldInGrid, Position[] newInLibrary){
        String title=""+username + "'s library   ";
        String title_space= PrinterUtils.printEquivalentSpace(title);

        String bottom_header = "|   |";
        for(int colonna=1; colonna<=library[0].length;colonna++)
        {
            bottom_header+="| "+colonna+" |";
        }

        System.out.println("");
        for(int riga=0; riga<library.length; riga++)
        {
            if(riga==library.length/2)
                System.out.print(title+(riga+1) + " -> ");
            else
                System.out.print(title_space+(riga+1) + " -> ");
            for(int colonna=0; colonna<library[0].length; colonna++)
            {
                System.out.print("| "+library[riga][colonna]+" |");
            }
            System.out.println();
        }
        System.out.println(title_space+bottom_header);
        if(newInLibrary!= null && oldInGrid!= null) {
            int row, column;
            System.out.print("New element in library in position: ");
            for (Position position : newInLibrary) {
                row = position.getRow() + 1;
                column = position.getColumn() + 1;
                System.out.print("(" + row + ";" + column + ")  ");
            }
            System.out.println("");
            System.out.print("Grid's position now with nothings: ");
            for (Position position : oldInGrid) {
                row = position.getRow() + 1;
                column = position.getColumn() + 1;
                System.out.print("(" + row + ";" + column + ")  ");
            }
            System.out.println("");
        }
    }

    /**It prints points (from common goal card) achieved from specific player
     * @param arg Message with all information about points achieved from common goal card. It re-used an existing class to
     * pass paramter. Player contained in arg is who has achieved common goal. Points gained are conteined in "getGainedPointsFirstCard".
     * New points available are contained in PointAvailable1 and PointAvailable2*/
    private void showPoint(MessageCommonGoal arg) {
        System.out.println("In " + arg.getPlayer() + "'s move: ");
        if(arg.getGainedPointsSecondCard()==1)
            System.out.println("First common goal card has been reached");
        else if(arg.getGainedPointsSecondCard()==2)
            System.out.println("Second common goal card has been reached");
        else if(arg.getGainedPointsSecondCard() == 3)
            System.out.println("Both commons goal card has been reached");
        System.out.println("He has " + arg.getGainedPointsFirstCard() + " points now");
        System.out.println("Point for common goal card 1: " + arg.getPointAvailable1());
        System.out.println("Point for common goal card 2: " + arg.getPointAvailable2());
    }

    /**
     * Prints a message to be displayed as an error occurs when creating a client
     * @param chosenAddress
     * @param chosenPort
     */
    @Override
    public void errorCreatingClient(String chosenAddress, int chosenPort) {
        System.out.println("Error >> It was impossible to create a client and contact the server at [" + chosenAddress + "," + chosenPort + "]");
    }



    /**Prints a personal goal card
     * @author: Riccardo Figini
     * @param goalVector Personal goal card vector
     * */
    private void printPersonalGoal(ArrayList<Couple> goalVector) {
        Position p;
        Color c;

        ObjectCard[][] matrix = new ObjectCard[6][5];
        for(int i=0; i<goalVector.size(); i++){
            p = (Position) (goalVector.get(i).getFirst());
            c = (Color) (goalVector.get(i).getSecond());
            matrix[p.getRow()][p.getColumn()]= new ObjectCard("",c, Type.FIRST);
        }

        for(int riga =0; riga<6;riga++)
        {
            for(int colonna =0; colonna<5;colonna++)
            {
                if(matrix[riga][colonna]==null)
                {
                    matrix[riga][colonna]=new ObjectCard("",Color.EMPTY,Type.FIRST);
                }
            }
        }

        showLibrary(matrix,"Your Personal Goal Card is:", null, null);
    }


    /**
     * Method used to ask a generic input to a user
     * @return
     */
    private String getInputRequest()
    {
        synchronized (this)
        {
            state=ClientState.REQUEST;
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return inputRequestBuffer;
    }


    /**
     * Asks the username for the client
     * @return
     */
    public String askUsername() {
        System.out.print(">> Type your username: ");
        return getInputRequest();
    }


    /**
     * Asks the technology to use for the game
     * @return
     */
    public int askTechnology() {
        String input;
        int parsedInput=0;
        boolean badInput;
        do
        {
            badInput=false;
            System.out.print(">> Select the communication technology to use [RMI = '0'; Socket = '1']: ");
            input = getInputRequest();
            try
            {
                parsedInput = Integer.parseInt(input);
                if (parsedInput > 1 || parsedInput < 0)
                    badInput = true;
            } catch (NumberFormatException e) {
                badInput = true;
            }
            finally
            {
                if(badInput)
                    System.out.println("ERROR >> You typed an invalid input, please retry.\n");
            }
        }while (badInput);
        return parsedInput;
    }


    /**
     * Asks the remote server IP
     * @return
     */
    public String askAddress() {
        String input;
        System.out.print(">> Type a server address [Default: 'localhost']: ");
        input = getInputRequest();
        if(input.equals(""))
            return "localhost";
        else
            return input;
    }


    /**
     * Asks the remote server port number
     * @param defaultPort
     * @return
     */
    public int askPort(int defaultPort) {
        String input;
        int parsedInput;
        boolean badInput;
        do
        {
            badInput=false;
            System.out.print(">> Type a server port number [Default: ");
            parsedInput=defaultPort;
            System.out.print(parsedInput+"]: ");
            input = getInputRequest();
            if(!input.equals(""))
            {
                try
                {
                    parsedInput = Integer.parseInt(input);
                    if (parsedInput <= 0)
                        badInput = true;
                } catch (NumberFormatException e) {
                    badInput = true;
                }
                finally
                {
                    if(badInput)
                        System.out.println("ERROR >> You typed an invalid input, please retry.\n");
                }
            }
        }while (badInput);
        return parsedInput;
    }


    /**Ask position in which extract cards
     * @author: Riccardo Figini
     * @param numberOfCards Number of card in input
     * @return {@code Position[]} Vector with position
     * */
    private Position[] askPositions(int numberOfCards) throws ResetMoveException {
        int row, column;
        Position[] positions = new Position[numberOfCards];
        System.out.println("Now draw "+(numberOfCards+1)+" cards");
        for(int i=0; i<numberOfCards; i++){
            System.out.println("Card number " + (i+1));
            System.out.println("Row: [type \"RESET\" to cancel]");
            row= getNumberWithLimit(10)-1;
            System.out.println("Column: [type \"RESET\" to cancel]");
            column= getNumberWithLimit(10)-1;
            positions[i] = new Position(row, column);
        }
        return positions;
    }


    /**Get a generic number with lower limit = 0 and upper limit as parameter
     * @author: Riccardo Figini
     * @param limit Upper limit
     * @return {@code int} inseted number
     * */
    private int getNumberWithLimit(int limit) throws ResetMoveException {
        String input;
        int number;
        input = getInputRequest();
        while (true) {
            try {
                if(input.equals("RESET"))
                    throw new ResetMoveException();
                number = Integer.parseInt(input);
                if(number<=0 || number>limit)
                    throw new NumberFormatException();
                return number;
            } catch (NumberFormatException e) {
                System.out.println("That is not a good number! Try again...");
            }
            input = getInputRequest();
        }
    }


    @Override
    public void almostOver(AlmostOverMessage arg) {
        System.out.println(">> "+arg.getFillerName()+"is the first player to complete his library, "+arg.getFillerPoints()+" will be assigned to him");
    }

    private void showCentralPoints(int centralPointCard) {
        System.out.println("First player to complete his library gets "+centralPointCard+"points");
    }


    /**
     * Thread that ask input in loop, and it notifies the observers with the collected inputs
     */
    @Override
    public void run()
    {
        String input;
        OBS_Message msg;
        while (true)
        {
            input=scanner.nextLine();
            synchronized (this)
            {
                if (state == ClientState.CHAT)
                {
                    if(chatAvailable)
                    {
                        msg = new OBS_ChatMessage(input);
                        setChanged();
                        notifyObservers(msg);
                    }
                }
                else if (state == ClientState.REQUEST)
                {
                    inputRequestBuffer=input;
                    this.notifyAll();
                    state=ClientState.CHAT;
                }
            }
        }
    }


    /**
     * Method handles messages coming from the model
     * @param o
     * @param arg
     */
    @Override
    public void update(ClientModel o, Message arg) {
        switch (arg.getType())
        {
            case SETUP_MESSAGE -> {
                SetupMessage msg = (SetupMessage) arg;

                showGrid(msg.getGrid(),MessageGrid.TypeOfGridMessage.INIT);
                for (int i=0; i<msg.getPlayersName().length;i++)
                {
                    showLibrary(msg.getPlayersLibraries()[i],msg.getPlayersName()[i],null,null);
                }
                showCentralPoints(msg.getCentralPointCard());
            }
            case UPDATE_GRID_MESSAGE -> {
                ObjectCard[][] obs = ((MessageGrid) arg).getGrid();
                showGrid(obs, ((MessageGrid) arg).getTypeOfGridMessage());
            }
            case UPDATE_LIBRARY_MESSAGE -> {
                ObjectCard[][] obs = ((MessageLibrary) arg).getLibrary();
                showLibrary(obs, ((MessageLibrary) arg).getOwnerOfLibrary(),((MessageLibrary) arg).getCardInGrid(), ((MessageLibrary) arg).getCardInLibr() );
            }
            case COMMON_GOAL -> showPoint( (MessageCommonGoal) arg);
            case ALMOST_OVER -> almostOver((AlmostOverMessage) arg);
        }
    }
    /**It is used when occurs an error an game will be closed
     * @author: Riccardo Figini
     * @author: Message with error
     * */
    @Override
    public void closeGame(String string) {
        System.out.println("Error! Game will be close");
        System.out.println(string);
        System.out.println("Press entry to close the window");
        Scanner in = new Scanner(System.in);
        in.nextLine();
        System.exit(0);
    }
}
