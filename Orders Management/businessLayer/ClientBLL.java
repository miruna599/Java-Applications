package businessLayer;

import dataAccessLayer.ClientQueries;
import model.Client;

import java.util.ArrayList;

/**
 * "ClientBLL" este clasa reprezentativa implementarii logicii
 * aplicatiei in ceea ce priveste tabela "Client".
 * Fiecare metoda cu nume sugestiv va apela cate o metoda din
 * clasa "-nume tabel-.Queries" logic asociata.
 * Fiecare metoda va respecta tipul metodei din clasa
 * logic asociata, precum si parametrii acesteia.
 * Aceasta clasa va apela metodele din "ClientQueries".
 * @author Stefanovici Miruna
 */
public class ClientBLL {

    public ClientBLL()
    {

    }

    public int adaugaClient(Client client) {


        return ClientQueries.insert(client);
    }

    public int stergeClient(String nume)
    {

        return ClientQueries.delete(nume);
    }

    public ArrayList<Client> selecteazaTotiClientii()
    {
        ArrayList<Client> listaClienti = new ArrayList<Client>();
        listaClienti = ClientQueries.select();
        return listaClienti;
    }
}
