package dataAccessLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Comenzi;

/**
 * "ComenziQueries" este clasa ce contine metodele reprezentative operatiilor SQL pentru
 * baza de date. In fiecare metoda se va face conexiunea la baza de date.
 */
public class ComenziQueries {

    protected static final Logger LOGGER = Logger.getLogger(ComenziQueries.class.getName());
    private static final String insertStatementString = "INSERT INTO comenzi (id,client,produs,cantitate)" + " VALUES (?,?,?,?)";
    private static final String deleteStatementString = "DELETE FROM comenzi WHERE client=?";
    private static final String selectStatementString = "SELECT * FROM comenzi ";
    private final static String findStatementString = "SELECT * FROM comenzi where client = ?";
    private final static String findProductStatementString = "SELECT * FROM comenzi where produs = ?";
    private static final String deleteProductStatementString = "DELETE FROM comenzi WHERE produs=?";
    /**
     * Metoda are rolul de a sterge din tabela "Comenzi", comenzile de
     * anumite produse.
     * Simbolul '?' va fi inlocuit de parametrul "numeProdus".
     * @param numeProdus - numele produsului cautat
     * @return
     */
    public static int deleteProductOrder(String numeProdus) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement deleteStatement = null;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteProductStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, numeProdus);

            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ComenziQueries:delete product order" + e.getMessage());
        } finally {
            DataBaseConnection.close(deleteStatement);
            DataBaseConnection.close(dbConnection);
        }
        return 0;
    }
    /**
     *
     *  Metoda are rolul de a cauta comenzile de anumite produse in baza de date. Se declara o
     *  variabila "exista" care are initial valoarea 0. Se realizeaza conexiunea la baza
     *  de date si se declara RestulSet-ul "rs". Simbolul '?' va fi inlocuit cu numele produsului
     *  cautat. Astfel, dupa ce se executa query-ul, se verifica pentru fiecare rand, daca numele produsului
     *  respectiv este egal cu cel cautat. Daca este egal, inseamna ca anumita comanda a fost gasita,
     *  "exista" devine 1 si se iese din bucla.
     *
     * @return - se va returna 1 daca exista comenzi cu produsul respectiv sau 0 in caz contrar
     */
    public static int findByNameProduct(String numeProdus) {
        int exista = 0;
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String nume = null;
        try {
            findStatement = dbConnection.prepareStatement(findProductStatementString);
            findStatement.setString(1, numeProdus);
            rs = findStatement.executeQuery();
            while(rs.next())
            {
                nume = rs.getString("Produs");
                if ( nume.contentEquals(numeProdus))
                    exista = 1;
                break;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ComenziQueries:findByName " + e.getMessage());
        } finally {
            DataBaseConnection.close(rs);
            DataBaseConnection.close(findStatement);
            DataBaseConnection.close(dbConnection);
        }
        return exista;
    }
    /**
     *
     *  Metoda are rolul de a cauta comenzile unui anumit client in baza de date. Se declara o
     *  variabila "exista" care are initial valoarea 0. Se realizeaza conexiunea la baza
     *  de date si se declara RestulSet-ul "rs". Simbolul '?' va fi inlocuit cu numele clientului
     *  cautat. Astfel, dupa ce se executa query-ul, se verifica pentru fiecare rand, daca numele clientului
     *  respectiv este egal cu cel cautat. Daca este egal, inseamna ca anumita comanda a fost gasita,
     *  "exista" devine 1 si se iese din bucla.
     *
     * @return - se va returna 1 daca exista comenzi alei clientului respectiv sau 0 in caz contrar
     */
    public static int findByName(String numeClient) {
        int exista = 0;
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String nume = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, numeClient);
            rs = findStatement.executeQuery();
            while(rs.next())
            {
                nume = rs.getString("Client");
                if ( nume.contentEquals(numeClient))
                    exista = 1;
                break;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ComenziQueries:findByName " + e.getMessage());
        } finally {
            DataBaseConnection.close(rs);
            DataBaseConnection.close(findStatement);
            DataBaseConnection.close(dbConnection);
        }
        return exista;
    }
    /**
     *  Se declara un obiect de tip "Comenzi" si un ArrayList de "Comenzi".
     *  Se declara un statement ce va reprezenta query-ul SQL din string-ul selectStatementString.
     *  Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     *  se va afisa mesajul aferent warning-ului.
     *  Obiectul de tip ResultSet va contine rezultatul query-ului, fiecare element al acestui ResultSet
     *  reprezentand un rand din tabel.Valorile fiecarei celule din acel tabel poate fi extras prin specificarea
     *  numelui coloanei asociate.Datele sunt astfel extrase, obiectul de tip "Comenzi" este format si
     *  adaugat in lista de comenzi.
     *  Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     *  a statemenet-ului, a resultSet-ului si a conexiunii.
     *
     *
     * @return - se va returna lista de comenzi formata
     */
    public static ArrayList<Comenzi> select() {
        Comenzi comanda = null;
        ArrayList<Comenzi> listaComenzi = new ArrayList<Comenzi>();
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        try {
            selectStatement = dbConnection.prepareStatement(selectStatementString);
            rs = selectStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("Id");
                String client = rs.getString("Client");
                String produs = rs.getString("Produs");
                int cantitate = rs.getInt("Cantitate");
                comanda = new Comenzi(id, client, produs, cantitate);
                listaComenzi.add(comanda);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ComenziQueries:select all " + e.getMessage());
        } finally {
            DataBaseConnection.close(rs);
            DataBaseConnection.close(selectStatement);
            DataBaseConnection.close(dbConnection);
        }
        return listaComenzi;
    }

    /**
     * Metoda are rolul de a sterge din tabela "Comenzi", comenzile unui anumit client.
     * Simbolul '?' va fi inlocuit de parametrul "nume".
     * @param nume - numele clientului cautat
     * @return
     */
    public static int deleteClientOrder(String nume) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement deleteStatement = null;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, nume);

            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ComenziQueries:delete client order" + e.getMessage());
        } finally {
            DataBaseConnection.close(deleteStatement);
            DataBaseConnection.close(dbConnection);
        }
        return 0;
    }

    /**
     * Se declara un statement ce va reprezenta query-ul SQL din string-ul insertStatementString.
     * Simbolurile '?' vor fi inlocuite cu variabilele instanta ale obiectului de tip "Comenzi".
     * Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     * se va afisa mesajul aferent warning-ului.
     * Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     * a statemenet-ului si a conexiunii.
     * @param comanda - un obiect de tip Comenzi
     *
     */
    public static int insert(Comenzi comanda) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement insertStatement = null;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setInt(1, comanda.getId());
            insertStatement.setString(2, comanda.getClient());
            insertStatement.setString(3, comanda.getProdus());
            insertStatement.setInt(4, comanda.getCantitate());
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ComenziQueries:insert " + e.getMessage());
        } finally {
            DataBaseConnection.close(insertStatement);
            DataBaseConnection.close(dbConnection);
        }
       return 0;
    }
}
