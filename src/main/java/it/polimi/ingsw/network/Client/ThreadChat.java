package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.network.Messages.ChatMessage;

import java.io.IOException;
import java.util.Scanner;

public class ThreadChat implements Runnable{
    Client client;
    Scanner scanner;
    public ThreadChat(Client client, Scanner scanner) {
        this.client=client;
        this.scanner=scanner;
    }

    @Override
    public void run() {
        String input;
        System.out.println("Chat is on");
        while (true)
        {
            input= scanner.nextLine();
            try {
                client.sendMessage(new ChatMessage(client.getUsername(),input));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
