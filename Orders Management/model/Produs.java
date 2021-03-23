package model;

/**
 * "Produs" este clasa ce reprezinta elementele ce alcatuiesc tabela "Produs".
 * Tabela contine produsele ce pot fi comandate de catre clienti.
 * Clasa contine ca metode cate un getter pentru fiecare variabila, cate un setter
 * pentru fiecare variabila si metoda toString().
 * @author Stefanovici Miruna
 *
 */
public class Produs {
    /**
     * Numele produsului
     */
    private String nume;
    /**
     * Pretul produsului
     */
    private float pret;

    public Produs(String nume, float pret) {
        this.nume = nume;
        this.pret = pret;
    }

    public String getNume() {
        return nume;
    }

    public float getPret() {
        return pret;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String toString() {
        return "Produs : nume=" + nume + ", pret=" + pret;
    }
}
