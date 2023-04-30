module MyShelfie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires com.google.gson;

    opens it.polimi.ingsw.View;
    exports it.polimi.ingsw.controller.guiControllers;
    exports it.polimi.ingsw.Network.Servers.RMI;
    exports it.polimi.ingsw.Network.Client;
}