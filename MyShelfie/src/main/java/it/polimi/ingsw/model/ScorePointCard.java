package it.polimi.ingsw.model;

public class ScorePointCard {
    private int score;

    private final int numberOfPlayer;

    /**Constructor
     * @author: Riccardo Figini
     * @param : numberOfPlayer number of player*/
    public ScorePointCard(int numberOfPlayer){
        this.numberOfPlayer=numberOfPlayer;
        score=8;
    }
    /**Methods that decreases/change score card, called when players reach common goal
     * @author: Riccardo Figini
     * */
    private void changeScore(){
        //TODO cambio dell'immagine
        switch (score){
            case 8:
                switch (numberOfPlayer){
                    case 2: score=4; break;
                    case 3,4: score = 6; break;
                    default:break;
                }break;
            case 6: score = 4;break;
            case 4: score =2;break;
            case 2: score = 0;break;
            default:break;
        }
    }
    /**Method that return current common goal's point. Automatically decrease the
     * score of common goal card (calling changeScore)
     * @author: Riccardo Figini
     * @return : number: score */
    public int getScore() {
        int number = score;
        changeScore();
        return number;
    }
}
