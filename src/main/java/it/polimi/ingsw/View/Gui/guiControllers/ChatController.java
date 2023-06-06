package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.View.OBSMessages.OBS_ChatMessage;
import it.polimi.ingsw.model.Enums.Color;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

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

    private void manage(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String text = textArea.getText().trim();
            if (text.length() > 0) {
                textArea.clear();
                printOwnMessage(text);
                ViewFactory viewFactory = ViewFactory.getInstance();
                viewFactory.notifyAllOBS(new OBS_ChatMessage(text));
            }
        }
    }
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
    public void printMessage(String text, String user){
        Pos pos;
        if(user.equals(ViewFactory.getInstance().getClientModel().getMyName()))
            return;
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
    private String getFreeColor() {
        String color = freeColor.get(index);
        index++;
        return color;
    }
}
