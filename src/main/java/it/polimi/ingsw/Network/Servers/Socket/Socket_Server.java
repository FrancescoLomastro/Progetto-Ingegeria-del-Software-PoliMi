package it.polimi.ingsw.Network.Servers.Socket;



import it.polimi.ingsw.controller.ServerReceiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class serves as server for the management of network communication via Socket technology.
 * The class takes care of receiving messages by creating the serverReceiver that will be used for receiving Socket message.
 *
 * @author Alberto Aniballi
 */
public class Socket_Server extends Thread{
    private ServerReceiver serverReceiver;
    private final int port;
    private ServerSocket serverSocketAcceptor;

    /**
     * Constructor of Socket_Server instances.
     * It is used to instantiate a new serverReceiver that will listen to a port dedicated to socket communication.
     *
     * @param serverReceiver: it is the controller that will act as serverReceiver.
     * @author Alberto Aniballi
     */
    public Socket_Server(ServerReceiver serverReceiver, int socketPort) {
        this.serverReceiver = serverReceiver;
        this.port=socketPort;
    }

    /**
     * This method activates the socket connection server-side instantiating an ServerSocket object using a specific socket port.
     * Moreover, it starts the thread that will continuously listen and in case accept socket client requests.
     *
     * @author Alberto Aniballi
     */
    public void turnOn() throws IOException {
        serverSocketAcceptor = new ServerSocket(port);
        start();
    }

    /**
     * This method activates the server-side thread used to accept client requests and
     * to activate the socket connection thread which will actually read and respond to client requests.
     *
     * @author Alberto Aniballi
     */
    @Override
    public void run()
    {
        System.out.println("Server Socket listening to all interfaces");
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
