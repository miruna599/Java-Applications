package businessLayer;

import dataAccessLayer.ClientQueries;
import dataAccessLayer.ComenziQueries;
import dataAccessLayer.ProdusQueries;
import model.Client;
import model.Comenzi;

import java.util.ArrayList;
/**
 * "ComenziBLL" este clasa reprezentativa implementarii logicii
 * aplicatiei in ceea ce priveste tabela "Comenzi".
 * Fiecare metoda cu nume sugestiv va apela cate o metoda din
 * clasa "-nume tabel-.Queries" logic asociata.
 * Fiecare metoda va respecta tipul metodei din clasa
 * logic asociata, precum si parametrii acesteia.
 * Aceasta clasa va apela metodele din "ComenziQueries".
 * @author Stefanovici Miruna
 */
public class ComenziBLL {

    public ComenziBLL()
    {

    }

    public int adaugaComanda(Comenzi comanda) {

        return ComenziQueries.insert(comanda);
    }

    public int stergeComandaAUnuiClient(String nume) {

        return ComenziQueries.deleteClientOrder(nume);
    }
    public int stergeComandaCuProdus(String numeProdus) {

        return ComenziQueries.deleteProductOrder(numeProdus);
    }

    public ArrayList<Comenzi> selecteazaToateComenzile()
    {
        ArrayList<Comenzi> listaComenzi = new ArrayList<Comenzi>();
        listaComenzi = ComenziQueries.select();
        return listaComenzi;
    }

    public int cautaComandaClient(String numeClient)
    {
        return ComenziQueries.findByName(numeClient);
    }
    public int cautaComandaProdus(String numeProdus)
    {
        return ComenziQueries.findByNameProduct(numeProdus);
    }
}
