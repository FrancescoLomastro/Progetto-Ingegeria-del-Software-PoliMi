module MyShelfie {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    requires java.rmi;

    opens it.polimi.ingsw to javafx.fxml;
    exports it.polimi.ingsw;
    exports it.polimi.ingsw.controller;
    exports it.polimi.ingsw.View.Gui.guiControllers;
    exports it.polimi.ingsw.exceptions;
    exports it.polimi.ingsw.model;
    exports it.polimi.ingsw.model.CardGenerator;
    exports it.polimi.ingsw.model.Cards;
    exports it.polimi.ingsw.model.Cards.ConcreteCommonCards;
    exports it.polimi.ingsw.model.Enums;
    exports it.polimi.ingsw.model.LivingRoom;
    exports it.polimi.ingsw.model.Player;
    exports it.polimi.ingsw.model.Utility;
    exports it.polimi.ingsw.Network.Client;
    exports it.polimi.ingsw.Network.Client.RMI;
    exports it.polimi.ingsw.Network.Client.Socket;
    exports it.polimi.ingsw.Network.Messages;
    exports it.polimi.ingsw.Network.ObserverImplementation;
    exports it.polimi.ingsw.Network.Servers;
    exports it.polimi.ingsw.Network.Servers.RMI;
    exports it.polimi.ingsw.Network.Servers.Socket;
    exports it.polimi.ingsw.View;
    exports it.polimi.ingsw.View.Gui;
    exports it.polimi.ingsw.View.Cli;
}