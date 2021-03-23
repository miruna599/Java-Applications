import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread {

    private BlockingQueue<Client> clients;
    private int waitingPeriod;
    private boolean control=true;
    private volatile boolean queueNotEmpty;
    private float sumW;

    public float  getSumW ()
    {
        return sumW;
    }
    public void setQueueNotEmpty(boolean queueNotEmpty) {
        this.queueNotEmpty = queueNotEmpty;
    }

    public boolean getQueueNotEmpty() {
        return queueNotEmpty;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public int  getWaitingPeriod()
    {
        return this.waitingPeriod;
    }

    public void setWaitingPeriod(int waitingPeriod)
    {
        this.waitingPeriod=waitingPeriod;
    }

    public Server()
    {
        this.clients = new LinkedBlockingQueue<>();
        this.queueNotEmpty = false;
    }

    public void addClient(Client newClient) {
       clients.add(newClient);
       waitingPeriod+=newClient.gettService();
    }

    public void run ()
    {
        while(control)
        {
            if(!this.clients.isEmpty())
                {
                    Client unC = clients.peek();
                  while(unC.gettService()>0) {

                      try {
                          sleep(1000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }

                      sumW += clients.size();
                      unC.settService(unC.gettService() - 1);
                      this.waitingPeriod--;
                  }

                    clients.remove(unC);
                    if(clients.isEmpty())
                        this.queueNotEmpty=false;

                }

        }
    }

    public BlockingQueue<Client> getListOfClients()
    {
        return clients;
    }

    public String toString()
    {
        String s="";
        for ( Client unC : clients)
        {
            s+=unC.toString();
            s+=" ";
        }
        return s;
    }


}
