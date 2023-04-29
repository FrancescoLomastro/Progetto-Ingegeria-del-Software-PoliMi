package it.polimi.ingsw.Network.Servers.Socket;



import it.polimi.ingsw.controller.ServerReceiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Socket_Server extends Thread{
    private ServerReceiver serverReceiver;
    private final int port;
    private ServerSocket serverSocketAcceptor;
    public Socket_Server(ServerReceiver serverReceiver, int rmiPort) {
        this.serverReceiver = serverReceiver;
        this.port=rmiPort;
    }

    public void turnOn() throws IOException {
        serverSocketAcceptor = new ServerSocket(port);
        start();
    }

    @Override
    public void run()
    {
        System.out.println("Server Socket on listening to all interfaces");
        while (true)
        {
            Socket clientSocket=null;
            Thread thread=null;
            try {
                clientSocket = serverSocketAcceptor.accept();
            } catch (IOException e) {
                System.out.println("Failed to connect to remote client\n[Details]\n"+e);
            }
            try {
                new Thread(new SocketConnection(serverReceiver,clientSocket)).start();
            } catch (IOException e) {
                System.out.println("Failed creating socket for remote client\n[Details]\n"+e);
                System.out.println("Closing socket...");
                try {
                    clientSocket.close();
                    thread.interrupt();
                } catch (IOException ex) {
                    System.out.println("Failed closing a socket"+ex);
                }
            }

        }
    }
}
