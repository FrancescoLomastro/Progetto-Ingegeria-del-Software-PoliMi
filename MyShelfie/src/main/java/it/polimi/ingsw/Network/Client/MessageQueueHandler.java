package it.polimi.ingsw.Network.Client;


public class MessageQueueHandler implements Runnable {
    private final Client client;
    public MessageQueueHandler(Client client) {
        this.client=client;
    }

    @Override
    public void run() {
        ArrayList<Message> list=null;
        while (true)
        {
            list=client.getMessageQueue();
            if(list.size()!=0)
            {
                list.forEach(x->client.updateView(x));
            }
            else
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
