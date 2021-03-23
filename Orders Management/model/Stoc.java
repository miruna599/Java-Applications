package model;

/**
 * "Stoc" este clasa ce reprezinta elementele tabelei "Stoc".
 * Fiecarui produs adaugat in tabela "Produs" i se asociaza
 * o cantitatea.Numele produsului respectiv si cantitatea
 * asociata se gasesc in tabela "Stoc".
 * Clasa contine ca metode cate un getter pentru fiecare variabila, cate un setter
 * pentru fiecare variabila si metoda toString().
 * @author Stefanovici Miruna
 */
public class Stoc {
    /**
     * Numele produsului
     */
    private String produs;
    /**
     * Cantitatea totala a produsului
     */
    private int cantitate;

    public Stoc(String produs, int cantitate) {
        this.produs = produs;
        this.cantitate = cantitate;
    }

    public String getProdus() {
        return produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setProdus(String produs) {
        this.produs = produs;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String toString() {
        return "Stoc : produs=" + produs + ", cantitate=" + cantitate;
    }
}
