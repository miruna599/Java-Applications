package dataAccessLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Client;
import model.Produs;
import model.Stoc;

/**
 * "StocQueries" este clasa ce contine metodele reprezentative operatiilor SQL pentru
 * baza de date. In fiecare metoda se va face conexiunea la baza de date.
 */
public class StocQueries {

    protected static final Logger LOGGER = Logger.getLogger(StocQueries.class.getName());
    private static final String insertStatementString = "INSERT INTO stoc (produs,cantitate)" + " VALUES (?,?)";
    private static final String deleteStatementString = "DELETE FROM stoc WHERE produs=?";
    private static final String updateStatementString = "UPDATE stoc SET cantitate=cantitate+? WHERE produs =? ";
    private static final String updateMinusStatementString = "UPDATE stoc SET cantitate=cantitate-? WHERE produs =? ";
    private static final String selectStatementString = "SELECT * FROM stoc ";

    /**
     * Metoda are rolul de a verifica daca pentru un anumit produs, cerut de
     * client, exista stocul necesar. Se declara variabila "exista" si se
     * parcurg randurile tabelului. Cand se gaseste produsul respectiv, se
     * verifica daca stocul lui este mai mare sau ehal decat cantitatea
     * ceruta. Daca este mai are, atunci inseamna ca produsul poate fi cumparat.
     * @param numeProdus - produsul cautat
     * @param cantitateCeruta - cantitatea ceruta de catre client
     * @return - 1 daca exista stoc necesar sau 0 daca nu exista
     */
    public static int select(String numeProdus, int cantitateCeruta) {
        int exista=0;
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        try {
            selectStatement = dbConnection.prepareStatement(selectStatementString);
            rs = selectStatement.executeQuery();
            while(rs.next()) {
                String produs = rs.getString("Produs");
                int cantitate = rs.getInt("Cantitate");
                if( produs.contentEquals(numeProdus)&& cantitate>= cantitateCeruta)
                {
                    exista=1;
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"StocQueries:select " + e.getMessage());
        } finally {
            DataBaseConnection.close(rs);
            DataBaseConnection.close(selectStatement);
            DataBaseConnection.close(dbConnection);
        }
        return exista;
    }
    /**
     * Metoda are rolul de a actualiza stocul unui anumit produs,
     * atunci cand sunt cumparate de catre client produse de tipul
     * respectiv.Simbolurile '?' sunt inlocuite cu parametrii functiei.
     * @param produs - produsul al carui stoc trebuie actualizat
     * @param stocAdaugat - cantitatea care se scade din stocul curent
     *
     */
    public static int updateMinus(int stocAdaugat, String produs) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement updateStatement = null;

        try {
            updateStatement = dbConnection.prepareStatement(updateMinusStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, stocAdaugat);
            updateStatement.setString(2,produs);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StocQueries:update" + e.getMessage());
        } finally {
            DataBaseConnection.close(updateStatement);
            DataBaseConnection.close(dbConnection);
        }
        return 0;
    }
    /**
     * Metoda are rolul de a actualiza stocul unui anumit produs,
     * atunci cand sunt adaugate in plus produse de tipul respectiv.
     * Simbolurile '?' sunt inlocuite cu parametrii functiei.
     * @param produs - produsul al carui stoc trebuie actualizat
     * @param stocAdaugat - cantitatea care se adauga la stocul curent
     *
     */
    public static int updatePlus(int stocAdaugat, String produs) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement updateStatement = null;

        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, stocAdaugat);
            updateStatement.setString(2,produs);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StocQueries:update" + e.getMessage());
        } finally {
            DataBaseConnection.close(updateStatement);
            DataBaseConnection.close(dbConnection);
        }
        return 0;
    }

    /**
     * Metoda presupune stergerea stocului unui anumit produs.
     * Se declara un statement ce va reprezenta query-ul SQL din string-ul deleteStatementString.
     * '?' va fi inlocuit cu parametrul "produs" dat metodei.
     * Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     * se va afisa mesajul aferent warning-ului.
     * Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     * a statemenet-ului si a conexiunii.
     * @param produs - numele produsului al carui stoc se doreste a fi sters
     *
     */
    public static int delete(String produs) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement deleteStatement = null;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, produs);

            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StocQueries:delete" + e.getMessage());
        } finally {
            DataBaseConnection.close(deleteStatement);
            DataBaseConnection.close(dbConnection);
        }
        return 0;
    }


    /**
     * Se declara un statement ce va reprezenta query-ul SQL din string-ul insertStatementString.
     * Simbolurile '?' vor fi inlocuite cu variabilele instanta ale obiectului de tip "Stoc".
     * Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     * se va afisa mesajul aferent warning-ului.
     * Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     * a statemenet-ului si a conexiunii.
     * @param stoc - un obiect de tip Stoc
     *
     */
    public static int insert(Stoc stoc) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement insertStatement = null;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, stoc.getProdus());
            insertStatement.setInt(2, stoc.getCantitate());
            insertStatement.executeUpdate();
            ResultSet rs = insertStatement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StocQueries:insert " + e.getMessage());
        } finally {
            DataBaseConnection.close(insertStatement);
            DataBaseConnection.close(dbConnection);
        }
    return 0;
    }
}
