package it.polimi.ingsw.View;


import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Position;

import java.util.Scanner;

public class Cli extends View implements Observer<ClientModel,Message> {
    private final Scanner scanner;

    public Cli(ClientModel clientModel)
    {
        scanner = new Scanner(System.in);
        clientModel.addObserver(this);
    }



    @Override
    public String askUsername()
    {
        System.out.print("Type your username: ");
        return scanner.nextLine().trim();
    }



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


    //servirà per la chat
    @Override
    public void updateObserversWithMessage(Message message)
    {
        setChanged();
        notifyObservers(message);
    }



    @Override
    public void printAString(String s)
    {
        System.out.println(s);
    }



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



    @Override
    public String onInvalidUsername()
    {
        System.out.println("The typed username was already used, please type another username or try later");
        return askUsername();
    }



    @Override
    public void showGrid(ObjectCard[][] matrice){
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

            // Stampa la matrice con i bordi
            for (int riga = 0; riga < matrice.length; riga++) {
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
    @Override
    public void showLibrary(ObjectCard[][] library){
        for(int i=0; i<library.length; i++){
            for(int j=0; j<library[i].length; j++){
                System.out.print(library[i][j]);
            }
            System.out.println("");
        }
    }

    //da rivedere
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


    //da rivedere
    private void showPoint(MessageCommonGoal arg) {
        System.out.println("Common/s goal have been reached by: " + arg.getPlayer());
        System.out.println("He has " + arg.getGainedPointsFirstCard() + "points now");
        System.out.println("Point for common goal card 1: " + arg.getPointAvailable1());
        System.out.println("Point for common goal card 2: " + arg.getPointAvailable2());
    }
/*

    public void printAll(ClientModel clientObject) {
        showGrid();
        System.out.println("First common goal: " + clientObject.getDescriptionFirstCommonGoal());
        System.out.println("Second common goal: " + clientObject.getDescriptionSecondCommonGoal());
        System.out.println("Your personal goal:" );
        printPersonalGaol(clientObject.getGoalVector());
        Map<String, ObjectCard[][]> map = clientObject.getAllLibrary();
        for(Map.Entry<String, ObjectCard[][]> entry : map.entrySet() ){
            System.out.println(entry.getKey() +"'s library");
            showLibrary(entry.getValue());
        }
    }

    private void printPersonalGaol(ArrayList<Couple> goalVector) {
        for(int i=0; i<goalVector.size(); i++){
            Position p = (Position) (goalVector.get(i).getFirst());
            Color c = (Color) (goalVector.get(i).getSecond());
            System.out.println("row: " + p.getRow() + ", column: " + p.getColumn()+", color: "+ c);
        }
    }*/

   /*
    private Position[] askPositions(int numberOfCards)
    {
        Position[] positions = new Position[numberOfCards];
        threadOutputClient.printAString("Now draw");
        for(int i=0; i<numberOfCards; i++){
            threadOutputClient.printAString("Card number " + i);
            threadOutputClient.printAString("Row: ");
            numberOfCards= getNumberOfCards(10)-1;
            positions[i].setRow(numberOfCards);
            threadOutputClient.printAString("Column: ");
            numberOfCards= getNumberOfCards(10)-1;
            positions[i].setColumn(numberOfCards);
        }
        return positions;
    }
    private int getNumberOfCards(int limit)
    {
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
        }
    }*/
    /*
    //da rivedere
    private void askMove()  {
        int column, numberOfCards;
        Position[] position;
        //threadChat.interrupt();
        System.out.println("It's your turn, please make your move");

        System.out.println("How many card do you want? (minimum 1, max 3)");
        numberOfCards = getNumberOfCards(3);
        position = askPositions(numberOfCards);

        System.out.println("In which column do you want insert this cards?");
        column= getNumberOfCards(5)-1;

        Message reMessage = new MessageMove(position, column);

        try {
            client.sendMessage(reMessage);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to send message");
        }
        //threadChat.start();
    }

     */

}
