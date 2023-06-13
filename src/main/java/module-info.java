module MyShelfie {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    requires java.rmi;

    opens it.polimi.ingsw.view.Gui.guiControllers to javafx.fxml;
    opens it.polimi.ingsw to javafx.fxml;
    exports it.polimi.ingsw;
    exports it.polimi.ingsw.controller;
    exports it.polimi.ingsw.view.Gui.guiControllers;
    exports it.polimi.ingsw.exceptions;
    exports it.polimi.ingsw.model;
    exports it.polimi.ingsw.model.CardGenerator;
    exports it.polimi.ingsw.model.Cards;
    exports it.polimi.ingsw.model.Cards.ConcreteCommonCards;
    exports it.polimi.ingsw.enums;
    exports it.polimi.ingsw.model.LivingRoom;
    exports it.polimi.ingsw.model.Player;
    exports it.polimi.ingsw.utility;
    exports it.polimi.ingsw.network.Client;
    exports it.polimi.ingsw.network.Client.RMI;
    exports it.polimi.ingsw.network.Client.Socket;
    exports it.polimi.ingsw.network.Messages;
    exports it.polimi.ingsw.network.ObserverImplementation;
    exports it.polimi.ingsw.network.Servers;
    exports it.polimi.ingsw.network.Servers.RMI;
    exports it.polimi.ingsw.network.Servers.Socket;
    exports it.polimi.ingsw.view;
    exports it.polimi.ingsw.view.Gui;
    exports it.polimi.ingsw.view.Cli;
    exports it.polimi.ingsw.network;
    exports it.polimi.ingsw.view.Gui.guiControllers.BoardComponents;
    opens it.polimi.ingsw.view.Gui.guiControllers.BoardComponents to javafx.fxml;
}