package it.polimi.ingsw.View;
import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Utility.Couple;
import it.polimi.ingsw.model.Utility.Position;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
/**This class handles input and output in cli version
 * @author: Riccardo Figini*/
public class Cli extends View implements Observer<ClientModel,Message> {
    private final Scanner scanner;
    /**Constructor
     * @author: Riccardo Figini*/
    public Cli(ClientModel clientModel)
    {
        scanner = new Scanner(System.in);
        clientModel.addObserver(this);
    }
    /**Ask username
     * @author: Riccardo Figini
     * @return {@code String} username*/
    @Override
    public String askUsername()
    {
        System.out.print("Type your username: ");
        return scanner.nextLine().trim();
    }
    /**Ask type of technology to use during the game
     * @author: Riccardo Figini
     * @return {@code int} Chosen technology
     * */
    @Override
    public int askTechnology()
    {
        String input;
        int parsedInput=0;
        boolean badInput;
        do
        {
            badInput=false;
            System.out.println("Select the communication technology to use,");
            System.out.print("RMI = '0'; Socket = '1': ");
            input = scanner.nextLine();
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
                    System.out.println("ERROR: You typed an invalid input, please retry.\n");
            }
        }while (badInput);
        return parsedInput;
    }
    /**Ask addres to user
     * @author: Riccardo Figini
     * @return {@code String} Address
     * */
    @Override
    public String askAddress()
    {
        String input;
        System.out.print("Type a server address [Default: 'localhost']: ");
        input = scanner.nextLine().trim();
        if(input.equals(""))
            return "localhost";
        else
            return input;
    }
    /**Ask port (TCP port) to user
     * @author: Riccardo Figini
     * @param chosenTechnology Type of technology chosen
     * @param defaultPort Default port
     * @return {@code int} Port
     * */
    @Override
    public int askPort(int chosenTechnology, int defaultPort)
    {
        String input;
        int parsedInput;
        boolean badInput;
        do
        {
            badInput=false;
            System.out.print("Type a server port number [Default: ");
            parsedInput=defaultPort;
            System.out.print(parsedInput+"]: ");
            input = scanner.nextLine();
            if(!input.equals(""))
            {
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
                        System.out.println("ERROR: You typed an invalid input, please retry.\n");
                }
            }
        }while (badInput);
        return parsedInput;
    }
    /**Update chat
     * @author: Riccardo Figini
     * @param message Chat's message*/
    @Override
    public void updateObserversWithMessage(Message message)
    {
        setChanged();
        notifyObservers(message);
    }
    /**Print generic string
     * @author: Riccardo Figini
     * @param s String to print
     * */
    @Override
    public void printAString(String s)
    {
        System.out.println(s);
    }
    /**Ask number of player
     * @author: Riccardo Figini
     * @param min Minimum number of players
     * @param max Maximum number of player
     * @return {@code int} Number of player*/
    @Override
    public int askNumberOfPlayers(int min, int max)
    {
        String input;
        Boolean badInput;
        int value=0;
        do
        {
            System.out.println("You are the first player, please type the number of players,");
            System.out.print("[Number must be between " + min + " and " + max+"]: ");
            badInput=false;
            input = scanner.nextLine().trim();
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
                System.out.println("ERROR: You typed an invalid number, please retry\n");
            }
        }while(badInput);
        return value;
    }
    /**It handles invalid username chosen
     * @return {@code String} Username
     * */
    @Override
    public String onInvalidUsername()
    {
        System.out.println("The typed username was already used, please type another username or try later");
        return askUsername();
    }
    /**
     * Show grid
     * @author: Riccardo Figini
     * */
    @Override
    public void showGrid(ObjectCard[][] matrice){
        System.out.println("Here grid");
        /*for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }*/
        // Calcola la lunghezza massima di ogni colonna per allineare i bordi
        int[] larghezzaColonne = new int[matrice[0].length];
        for (int colonna = 0; colonna < matrice[0].length; colonna++) {
            int larghezzaMassima = 0;
            for (int riga = 0; riga < matrice.length; riga++) {
                String valoreStringa;
                if(matrice[riga][colonna]==null)
                {
                    valoreStringa = " ";
                }
                else
                    valoreStringa = ""+matrice[riga][colonna];

                larghezzaMassima = Math.max(larghezzaMassima, valoreStringa.length());
            }
            larghezzaColonne[colonna] = larghezzaMassima;
        }
        System.out.println("| N || 1 || 2 || 3 || 4 || 5 || 6 || 7 || 8 || 9 |");
        // Stampa la matrice con i bordi
        for (int riga = 0; riga < matrice.length; riga++) {
            System.out.print(riga+1 + " -> ");
            for (int colonna = 0; colonna < matrice[0].length; colonna++) {
                String valoreStringa;
                if(matrice[riga][colonna]==null)
                {
                    valoreStringa = " ";
                }
                else
                    valoreStringa = ""+matrice[riga][colonna];
                int spaziVuoti = larghezzaColonne[colonna] - valoreStringa.length();

                // Stampa il bordo sinistro della cella
                System.out.print("| ");

                // Stampa il valore della cella allineando a destra
                for (int i = 0; i < spaziVuoti; i++) {
                    System.out.print(" ");
                }
                System.out.print(valoreStringa);

                // Stampa il bordo destro della cella
                System.out.print(" |");
            }
            // Vai alla riga successiva
            System.out.println();
        }

    }
    /**
     * Show Library of specific player
     * @author: Riccardo Figini
     * @param library library to print
     * */
    @Override
    public void showLibrary(ObjectCard[][] library){
        if(library == null)
            System.err.println("Library is null, somethings goes wrong");
        System.out.println("Here library: ");
        for(int i=0; i<library.length; i++){
            for(int j=0; j<library[i].length; j++){
                System.out.print(library[i][j]);
            }
            System.out.println("");
        }
    }
    /**Ask cli to do something
     * @param arg type of message to manage
     * @param o client model
     * */
    @Override
    public void update(ClientModel o, Message arg) {
        switch (arg.getType())
        {
            case UPDATE_GRID_MESSAGE -> {
                ObjectCard[][] obs = ((MessageGrid) arg).getGrid();
                showGrid(obs);
            }
            case UPDATE_LIBRARY_MESSAGE -> {
                ObjectCard[][] obs = ((MessageLibrary) arg).getLibrary();
                showLibrary(obs);
            }
            case COMMON_GOAL -> showPoint( (MessageCommonGoal) arg);
        }
    }
    /**It prints points (from common goal card) achieved from specific player
     * @param arg Message with all information about points achieved from common goal card. It re-used an existing class to
     * pass paramter. Player contained in arg is who has achieved common goal. Points gained are conteined in "getGainedPointsFirstCard".
     * New points available are contained in PointAvailable1 and PointAvailable2*/
    private void showPoint(MessageCommonGoal arg) {
        System.out.println("Common/s goal have been reached by: " + arg.getPlayer());
        System.out.println("He has " + arg.getGainedPointsFirstCard() + "points now");
        System.out.println("Point for common goal card 1: " + arg.getPointAvailable1());
        System.out.println("Point for common goal card 2: " + arg.getPointAvailable2());
    }
    /**Print everything in client model
     * @author: Riccardo Figini
     * @param clientObject Copy of model kept by severer
     * */
    @Override
    public void printAll(ClientModel clientObject) {
        clearScreen();
        showGrid(clientObject.getGrid());
        System.out.println("First common goal: " + clientObject.getDescriptionFirstCommonGoal());
        System.out.println("Second common goal: " + clientObject.getDescriptionSecondCommonGoal());
        printPersonalGaol(clientObject.getGoalList());
        Map<String, ObjectCard[][]> map = clientObject.getAllLibrary();
        for(Map.Entry<String, ObjectCard[][]> entry : map.entrySet() ){
            System.out.println(entry.getKey() +"'s library");
            showLibrary(clientObject.getLibrary(entry.getKey()));
        }
    }
    /**Print personal goal card
     * @author: Riccardo Figini
     * @param goalVector Personal goal card vector
     * */
    private void printPersonalGaol(ArrayList<Couple> goalVector) {
        System.out.println("Here personal goal card: ");
        if(goalVector==null)
            System.err.println("Error, list of goal vector null");
        for(int i=0; i<goalVector.size(); i++){
            Position p = (Position) (goalVector.get(i).getFirst());
            Color c = (Color) (goalVector.get(i).getSecond());
            System.out.println("row: " + p.getRow() + ", column: " + p.getColumn()+", color: "+ c);
        }
    }
    /**Ask position in which extract cards
     * @author: Riccardo Figini
     * @param numberOfCards Number of card in input
     * @return {@code Position[]} Vector with position
     * */
    private Position[] askPositions(int numberOfCards)
    {
        int row, column;
        Position[] positions = new Position[numberOfCards];
        System.out.println("Now draw "+numberOfCards+" cards");
        for(int i=0; i<numberOfCards; i++){
            System.out.println("Card number " + i);
            System.out.println("Row: ");
            row= getNumberWithLimit(10)-1;
            System.out.println("Column: ");
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
    private int getNumberWithLimit(int limit)
    {
        String input;
        int number;
        input = scanner.nextLine().trim();
        while (true) {
            try {
                number = Integer.parseInt(input);
                if(number<=0 || number>limit)
                    throw new NumberFormatException();
                return number;
            } catch (NumberFormatException e) {
                System.out.println("That is not a good number! Try again...");
            }
            input = scanner.nextLine().trim();
        }
    }
    /**Ask move
     * @author: Riccardo Figini*
     * @return {@code messahe} Message with move (Column and positions)*/
    @Override
    public Message askMove()  {
        int column, numberOfCards;
        Position[] position;
        //threadChat.interrupt();
        System.out.println("It's your turn, please make your move");

        System.out.println("How many card do you want? (minimum 1, max 3)");
        numberOfCards = getNumberWithLimit(3);
        position = askPositions(numberOfCards);

        System.out.println("In which column do you want insert this cards?");
        column= getNumberWithLimit(5)-1;

        Message reMessage = new MessageMove(position, column);

        return reMessage;
        //threadChat.start();
    }

    /**Clear the screen
     * @author: Riccardo Figini
     * */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
