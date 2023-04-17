package it.polimi.ingsw.Network.Servers;

import org.example.Controller.Controller;
import org.example.Messages.Message;
import org.example.Servers.RMI.RMI_Server;
import org.example.Servers.Socket.Socket_Server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class CentralServer {
    private final int socketPort = 8000;
    private final int rmiPort = 9000;
    private Controller controller;
    private Map<String,Connection> clients;


    public CentralServer()
    {
        controller= new Controller();
        clients = new HashMap<>();
    }

    public void start()
    {
        RMI_Server rmiServer = new RMI_Server(controller,rmiPort);
        try {
            rmiServer.turnOn();
        } catch (RemoteException e) {
            System.out.println("Failed to create Socket server"+e);
        } catch (AlreadyBoundException e) {
            System.out.println("Error while creating RMI server,an RMI server already exists"+e);
        }

        try {
            Socket_Server socketServer = new Socket_Server(controller, socketPort);
            socketServer.turnOn();
        } catch (IOException e) {
            System.out.println("Failed to create Socket server" + e);
        }
        System.out.println("I'm on");
//        /////////////////////PARTE DI PROVA////////////////////
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        for(Map.Entry<String,Connection> a : clients.entrySet())
//        {
//            try {
//                a.getValue().sendMessage(new BasicMessage("Server",MessageType.BENVENUTO));
//            } catch (SocketException e) {
//                System.out.println("Non sono riuscito a dare il benvenuto a "+a.getKey()+""+e);
//            } catch (IOException e) {
//                System.out.println("Non sono riuscito a dare il benvenuto a "+a.getKey()+""+e);
//            }
//        }
//        ///////////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        CentralServer centralServer = new CentralServer();
        centralServer.start();
    }

    public void login(String username,Connection connection)
    {
        controller.login(username,connection);
    }


    public void onMessage(Message message) {
        controller.onMessage(message);
    }



}
