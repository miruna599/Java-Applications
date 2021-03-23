import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.lang.String;
import java.util.ArrayList;

public class SimulationManager extends Thread {
    private String fileOut;
    private int numberOfClients;
    private int numberOfServers;
    private int tSimulation;
    private int tMinArrival;
    private int tMaxArrival;
    private int tMinService;
    private int tMaxService;
    ArrayList<Client> generatedClients;
    ArrayList<Server> servers;
    //ArrayList<Client> completedClients;

    public SimulationManager(String fileOut,int numberOfClients, int numberOfServers, int tSimulation, int tMinArrival, int tMaxArrival, int tMinService, int tMaxService) {
        this.fileOut = fileOut;
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        this.tSimulation = tSimulation;
        this.tMinArrival = tMinArrival;
        this.tMaxArrival = tMaxArrival;
        this.tMinService = tMinService;
        this.tMaxService = tMaxService;
        this.generatedClients = new ArrayList();
        this.servers = new ArrayList();
        completedClients = new ArrayList();
        generateRandomClients();
        Collections.sort(generatedClients);
        for (int i = 0; i < this.numberOfServers; i++) {
            Server newServer = new Server();
            servers.add(newServer);
            newServer.start();
        }
    }
    public void generateRandomClients() {
       Random rand = new Random();
            int id;
            int arrival;
            int service;
        for (int i = 0; i < this.numberOfClients; i++) {

            id = i + 1;
            arrival = rand.nextInt((this.tMaxArrival - this.tMinArrival) + 1) + this.tMinArrival;
            service = rand.nextInt((this.tMaxService - this.tMinService) + 1) + this.tMinService;
            Client newClient = new Client(id, arrival, service);
            generatedClients.add(newClient);
        }
    }
    public Server getMinimumWaitingTimeServer() {
        Server minServer = new Server();
        minServer.setWaitingPeriod(999999999);
        for (Server unS : servers)
            if (unS.getWaitingPeriod() < minServer.getWaitingPeriod()) {
                minServer=unS;
            }
        return minServer;
    }

    public int emptyServers()
    {   int ok=0;
        for(Server unS : servers)
        {
            if(unS.getQueueNotEmpty()==true) {
                ok = 1;
                break;
                }

        }
        if ( ok ==0 )
            return 1;
        else
            return 0;
    }
    public void stopThreads()
    {
        for(Server unS : servers)
            unS.setControl(false);

    }
    public float sumOfWaintingTimes()
    {
        float sum=0;
        for(Server oS: servers)
            sum+=oS.getSumW();
        return sum;
    }
    public void run() {
        int currentTime = 0;
        float numberVar=0;
        PrintWriter toWrite = null;
        try {
            toWrite = new PrintWriter(fileOut, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        while (currentTime < tSimulation  ) {
            Iterator<Client> iterateWaitingClients = generatedClients.iterator();
            while(iterateWaitingClients.hasNext()) {
                Client clientAux = iterateWaitingClients.next();
                if (currentTime == clientAux.gettArrival()) {
                    Server serverAux = getMinimumWaitingTimeServer();
                    serverAux.addClient(clientAux);
                    numberVar++;
                    serverAux.setQueueNotEmpty(true);
                    iterateWaitingClients.remove();
                }
            }
            toWrite.println("Time " + currentTime); toWrite.print("Waiting clients: ");
            for(Client unC : generatedClients)
                toWrite.print(unC.toString()+" ");
            toWrite.println();
            int countServers = 1;
            for (Server unS : servers)
            {
                if (unS.getListOfClients().isEmpty())
                    toWrite.printf("Queue %d: closed \n", countServers);
                else {
                    toWrite.printf("Queue %d: ", countServers);
                    toWrite.println(unS.toString());
                }
                countServers++;
            }
            if(generatedClients.isEmpty())
            {
                if(emptyServers()==1)
                    break;
            }
            currentTime++;
            toWrite.println();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopThreads();
        float result = sumOfWaintingTimes()/numberVar;
        toWrite.println();
        toWrite.println("Average time: "+result);
        toWrite.close();
}
    public static void main (String[] args) throws FileNotFoundException
    {   int a=0, b=0,  c=0, d1=0, d2=0, e1=0, e2=0;
        //File file = new File("D:\\UTCN\\An II\\Sem. II\\TP\\Laborator\\Tema2\\in-test-1.txt");
        File file = new File(args[0]);
        //String fileOut = "D:\\UTCN\\An II\\Sem. II\\TP\\Laborator\\Tema2\\out-test-1.txt";
        Scanner scan = new Scanner(file);

            a = Integer.parseInt(scan.next());
            b =  Integer.parseInt(scan.next());
            c =  Integer.parseInt(scan.next());
            String read;
            read = scan.next();
            String[] theSplit = read.split(",");
            d1 = Integer.parseInt(theSplit[0]);
            d2 = Integer.parseInt(theSplit[1]);
            read = scan.next();
            theSplit = read.split(",");
            e1 = Integer.parseInt(theSplit[0]);
            e2 = Integer.parseInt(theSplit[1]);

            SimulationManager simulation = new SimulationManager(args[1],a, b, c, d1, d2, e1, e2);

            simulation.start();

    }
}
