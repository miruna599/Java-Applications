package model;

/**
 * "Comenzi" este clasa ce reprezinta elementele ce alcatuiesc tabela "Comenzi".
 * Comenzile sunt facute de catre clienti : acestia comanda un anumit produs
 * intr-o anumitra cantitate.
 * Clasa contine ca metode cate un getter pentru fiecare variabila, cate un setter
 * pentru fiecare variabila si metoda toString().
 * @author Stefanovici Miruna
 */
public class Comenzi {
    /**
     * Identificatorul unic al comenzii
     */
    private int id;
    /**
     * Numele clientului ce plaseaza comanda
     */
    private String client;
    /**
     * Numele produsului comandat
     */
    private String produs;
    /**
     * Cantitatea dorita
     */
    private int cantitate;

    public Comenzi(int id, String client, String produs, int cantitate) {
        this.id = id;
        this.client = client;
        this.produs = produs;
        this.cantitate = cantitate;
    }

    public int getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public String getProdus() {
        return produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setProdus(String produs) {
        this.produs = produs;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String toString() {
        return "Comanda : " +  client + "," + produs+","+cantitate;
    }
}
