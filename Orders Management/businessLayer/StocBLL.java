package businessLayer;


import dataAccessLayer.StocQueries;
import model.Stoc;
/**
 * "StocBLL" este clasa reprezentativa implementarii logicii
 * aplicatiei in ceea ce priveste tabela "Stoc".
 * Fiecare metoda cu nume sugestiv va apela cate o metoda din
 * clasa "-nume tabel-.Queries" logic asociata.
 * Fiecare metoda va respecta tipul metodei din clasa
 * logic asociata, precum si parametrii acesteia.
 * Aceasta clasa va apela metodele din "StocQueries".
 * @author Stefanovici Miruna
 */
public class StocBLL {

    public StocBLL()
    {

    }

    public int adaugaStoc(Stoc stoc) {

        return StocQueries.insert(stoc);
    }

    public int stergeStoc(String produs) {

        return StocQueries.delete(produs);
    }
    public int actualizeazaCantitateAdaugare(int stocAdaugat, String produs) {

        return StocQueries.updatePlus(stocAdaugat, produs);
    }

    public int actualizeazaCantitateScadere(int stocScazut, String produs) {

        return StocQueries.updateMinus(stocScazut, produs);
    }

    public int verificaStoc(String numeProdus, int cantitateCeruta)
    {
        return StocQueries.select(numeProdus, cantitateCeruta);
    }

}
