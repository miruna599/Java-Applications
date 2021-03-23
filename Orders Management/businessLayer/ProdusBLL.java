package businessLayer;

import dataAccessLayer.ComenziQueries;
import dataAccessLayer.ProdusQueries;
import model.Comenzi;
import model.Produs;

import java.util.ArrayList;
/**
 * "ProdusBLL" este clasa reprezentativa implementarii logicii
 * aplicatiei in ceea ce priveste tabela "Produs".
 * Fiecare metoda cu nume sugestiv va apela cate o metoda din
 * clasa "-nume tabel-.Queries" logic asociata.
 * Fiecare metoda va respecta tipul metodei din clasa
 * logic asociata, precum si parametrii acesteia.
 * Aceasta clasa va apela metodele din "ProdusQueries".
 * @author Stefanovici Miruna
 */
public class ProdusBLL {

    public ProdusBLL()
    {

    }

    public int adaugaProdus(Produs produs) {

        return ProdusQueries.insert(produs);
    }

    public int stergeProdus(String nume) {

        return ProdusQueries.delete(nume);
    }

    public ArrayList<Produs> selecteazaToateProdusele()
    {
        ArrayList<Produs> listaProduse = new ArrayList<Produs>();
        listaProduse = ProdusQueries.select();
        return listaProduse;
    }

    public int cautaProdus(String numeProdus)
    {
        return ProdusQueries.findByName(numeProdus);
    }
    public float cautaPret(String numeProdus){
        return ProdusQueries.selectPrice(numeProdus);
    }
}
