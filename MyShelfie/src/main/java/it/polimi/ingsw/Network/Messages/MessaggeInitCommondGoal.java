package it.polimi.ingsw.Network.Messages;

public class MessaggeInitCommondGoal extends Message{
    private final String description1;
    private final String description2;
    public MessaggeInitCommondGoal(String d1, String d2){
        super(MessageType.INIT_COMMON_GOAL);
        description1=d1;
        description2=d2;
    }
    public String getDescription1() {
        return description1;
    }
    public String getDescription2() {
        return description2;
    }
}
