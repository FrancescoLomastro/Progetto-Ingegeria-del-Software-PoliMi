package it.polimi.ingsw.model;
public abstract class CommonGoalCard extends Card{
    private ScorePointCard scorePointCard;
    /**Constructor
     * @author Riccardo Figini
     * @param description description of common goal file (get from json file)
     * @param game current game
     * */
    public CommonGoalCard(String description, Game game){
        super(description);
        scorePointCard = new ScorePointCard(game.getNumPlayers());
    }
    /**Abstract function. Each subclass implements code to verify if
     * someone has reached the common goal
     * @author Riccardo Figini
     * @param lib player's library
     * @return boolean: true if the library respect common goal features */
    public abstract boolean isSatisfied(Library lib);

    /**This method get current common goal point
     * @author Riccardo Figini
     * @return scorePointCard.getScore() int score point*/
    public int getPoints(){
        return scorePointCard.getScore();
    }

}