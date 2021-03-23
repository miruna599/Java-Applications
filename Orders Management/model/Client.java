package model;

/**
 * "Client" este clasa ce reprezinta elementele ce alcatuiesc tabela "Client".
 * Un client va fi cel care poate plasa comenzi de anumite produse si cantitati.
 * Clasa contine ca metode cate un getter pentru fiecare variabila, cate un setter
 * pentru fiecare variabila si metoda toString().
 * @author Stefanovici Miruna
 */
public class Client {
    /**
     * Identificatorul unic al clientului
     *
     */
    private int id;
    /**
     * Numele clientului
     * */
    private String nume;
    /**
     * Adresa clientului
     */
    private String adresa;

    public Client(int id, String nume, String adresa) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String toString() {
        return "Client : id=" + id + ", nume=" + nume + ", adresa=" + adresa;
    }
}
