package it.polimi.ingsw.model;
public abstract class CommonGoalCard extends Card{
    private int points;
    private int numPlayers = 0;
    public CommonGoalCard(String description, Game game){

        super(description);
        points = 8;
        this.numPlayers = game.getNumPlayers();
    }
    public CommonGoalCard() {
    }
    public abstract boolean isSatisfied(Library lib);

    public int getPoints(){

        return points;
    }

    // PROBLEMA: I PUNTI VARIANO IN BASE AL NUMERO DI GIOCATORI: RISOLTO
    public void changePoints(){ // da chiamare ogni volta che l'obiettivo è soddisfatto

        switch(numPlayers){

            case 2 -> {

                switch (points){

                    case 8 -> points = 4;
                    case 4 -> points = 2;
                }
            }

            case 3 -> {

                switch(points){

                    case 8 -> points = 6;
                    case 6 -> points = 4;
                    case 4 -> points = 0;
                }
            }

            case 4 -> {

                switch(points){

                    case 8 -> points = 6;
                    case 6 -> points = 4;
                    case 4 -> points = 2;
                    case 2 -> points = 0;
                }
            }
        }
    }

}