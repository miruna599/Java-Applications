public class Client implements Comparable {

    private int idClient;
    private int tArrival;
    private int tService;

    public Client(int idClient, int tArrival, int tService)
    {
        this.idClient=idClient;
        this.tArrival=tArrival;
        this.tService=tService;
    }
    public Client() { }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int gettArrival() {
        return tArrival;
    }

    public void settArrival(int tArrival) {
        this.tArrival = tArrival;
    }

    public int gettService() {
        return tService;
    }

    public void settService(int tService) {
        this.tService = tService;
    }


    public int compareTo(Object clientToCompare)
    {
        int compareArrival = ((Client) clientToCompare).gettArrival();
        return this.tArrival-compareArrival;
    }

    public String toString ()
    {
        return "("+this.idClient+" "+this.tArrival+" "+this.tService+")";
    }
}
