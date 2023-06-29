package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.view.OBSMessages.OBS_ChatMessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.*;

/**
 * The class is dedicated to managing the chat and the messages that players write and send within it.
 * It is used as an intermediary between the fixed parts of the GUI of the 'Chat.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 * In addition, the class manages the interaction between the user and the various graphic components of the scene.
 *
 * @author Riccardo Figini
 */
public class ChatController implements Initializable {
    @FXML
    public AnchorPane exsterAnchor;
    @FXML
    public AnchorPane internAnchor;
    @FXML
    public BorderPane borderPane;
    @FXML
    public Label title;
    @FXML
    public TextArea textArea;
    @FXML
    public VBox vbox;
    @FXML
    public ScrollPane scrollPaneChat;
    @FXML
    public AnchorPane anchorScrollBar;
    String style = "-fx-background-color: #FFDEAD; -fx-background-radius: 20";
    Map<String, String> colorPlayer;
    int index;
    ArrayList<String> freeColor;

    /**
     * This method is the constructor of the chat controller.
     * In particular, data structures are initialised to manage the colour assignment of the different players within the chat.
     *
     * @author Riccardo Figini
     */
    public ChatController(){
        super();
        colorPlayer = new HashMap<>();
        freeColor = new ArrayList<>();
        freeColor.add("TAN");
        freeColor.add("STEELBLUE");
        freeColor.add("SADDLEBROWN");
        freeColor.add("PALEVIOLETRED");
        freeColor.add("RED");
        index=0;
    }

    /**
     * This method is used to initialize the controller of the "Chat.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Riccardo Figini
     * @author Alberto Aniballi
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        textArea.setOnKeyPressed(this::manage);
        vbox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                scrollPaneChat.setVvalue((Double) t1);
            }
        });

    }

    /**
     * This method is used to notify the chat observer in order to send the message
     * to other players once the enter key is pressed.
     *
     * @param keyEvent the event that triggers the activation of the method;
     * @author Riccardo Figini
     */
    private void manage(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String text = textArea.getText().trim();
            if (text.length() > 0) {
                textArea.clear();
                ViewFactory viewFactory = ViewFactory.getInstance();
                viewFactory.notifyAllOBS(new OBS_ChatMessage(text));
            }
        }
    }

    /**
     * This method is used to insert the text of the message within the chat of the player
     * who wrote and subsequently sent the message.
     *
     * @param text text message to add in the chat;
     * @author Riccardo Figini
     */
    private void printOwnMessage(String text) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_RIGHT);
        hBox.setPadding(new Insets(5,5,5,10));
        Text text1 = new Text(text);
        TextFlow textFlow = new TextFlow(text1);
        textFlow.setStyle(style);
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hBox.getChildren().add(textFlow);
        vbox.setAlignment(Pos.TOP_RIGHT);
        vbox.getChildren().add(hBox);
        scrollPaneChat.setVvalue(1.0);
    }

    /**
     * This method is used to insert the message text into the chats of players who receive the message sent by another player.
     * The name of the player who wrote the message will appear along with the message.
     *
     * @param text text message to add in the chat;
     * @param user user who sent the message;
     * @author Riccardo Figini
     */
    public void printMessage(String text, String user){
        Pos pos;
        String myName = ViewFactory.getInstance().getClientModel().getMyName();
        if(user.equals(myName) || user.equals("Private to "+myName))
        {
            printOwnMessage(text);
            return;
        }
        else if(user.equals("Server"))
             pos = Pos.TOP_CENTER;
        else
            pos = Pos.TOP_LEFT;
        HBox hBox = new HBox();
        hBox.setAlignment(pos);
        hBox.setPadding(new Insets(5,5,5,10));

        Text userText = new Text(user);
        Text messageText = new Text(text);
        Text enter = new Text("\n");
        userText.setStyle(selectUserStyle(user));
        messageText.setStyle(style);
        TextFlow textFlow = new TextFlow(userText, enter, messageText);
        textFlow.setStyle(style);
        userText = new Text(text);
        userText.setStyle(style);

        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hBox.getChildren().add(textFlow);
        vbox.setAlignment(pos);
        vbox.getChildren().add(hBox);
        scrollPaneChat.setVvalue(1.0);
    }

    /**
     * This method is used to select the specific style dedicated to each player in the game.
     *
     * @param user the specific user for whom to search the style;
     * @author Riccardo Figini
     */
    private String selectUserStyle(String user) {
        if (colorPlayer.containsKey(user))
            return colorPlayer.get(user);
        else {
            String color = getFreeColor();
            String tmp = "-fx-fill: " + color + ";-fx-font-size: 10px;-fx-font-weight: bold";
            colorPlayer.put(user, tmp);
            return tmp;
        }
    }

    /**
     * This method is used to select available colors to be used in user style.
     *
     * @author Riccardo Figini
     */
    private String getFreeColor() {
        String color = freeColor.get(index);
        index++;
        return color;
    }
}
