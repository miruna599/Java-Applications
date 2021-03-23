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
import model.Comenzi;
import model.Produs;
/**
 * "ProdusQueries" este clasa ce contine metodele reprezentative operatiilor SQL pentru
 * baza de date. In fiecare metoda se va face conexiunea la baza de date.
 */
public class ProdusQueries {

    protected static final Logger LOGGER = Logger.getLogger(ProdusQueries.class.getName());
    private static final String insertStatementString = "INSERT INTO produs (nume,pret)" + " VALUES (?,?)";
    private static final String deleteStatementString = "DELETE FROM produs WHERE nume=?";
    private static final String selectStatementString = "SELECT * FROM produs ";
    private final static String findStatementString = "SELECT * FROM produs where nume = ?";
    private static final String selectPriceStatementString = "SELECT * FROM produs ";

    /**
     * Metoda cauta pentru un anumit produs, pretul lui. Se declara variabila
     * "pret" care va tine valoarea pretului pentru produsul cu denumirea
     * "numeProdus". Se parcurg randurile tabelului, se salveaza valoarea
     * pretului in variabila"pret", iar atunci cand se gaseste
     * produsul cautat, se iese din bucla.
     * @param numeProdus - numele produsului al carui pret se cauta
     * @return - se returneaza pretul produsului respectiv
     */
    public static float selectPrice(String numeProdus) {
        float pret=0;

        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        try {
            selectStatement = dbConnection.prepareStatement(selectPriceStatementString);
            rs = selectStatement.executeQuery();
            while(rs.next()) {

                String nume = rs.getString("Nume");
                pret = rs.getFloat("Pret");
                if(nume.contains(numeProdus))
                {
                    break;
                }

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ComenziQueries:select price " + e.getMessage());
        } finally {
            DataBaseConnection.close(rs);
            DataBaseConnection.close(selectStatement);
            DataBaseConnection.close(dbConnection);
        }
        return pret;
    }
    /**
     *
     *  Metoda are rolul de a cauta un anumit produs in baza de date. Se declara o
     *  variabila "exista" care are initial valoarea 0. Se realizeaza conexiunea la baza
     *  de date si se declara RestulSet-ul "rs". Simbolul '?' va fi inlocuit cu numele produsului
     *  cautat. Astfel, dupa ce se executa query-ul, se verifica pentru fiecare rand, daca numele produsului
     *  respectiv este egal cu cel cautat. Daca este egal, inseamna ca produsul a fost gasit, "exista" devine
     *  1 si se iese din bucla.
     *
     * @return - se va returna 1 daca produsul exista in baza de date sau 0 in caz contrar
     */
    public static int findByName(String numeProdus) {
        int exista = 0;
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String nume = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, numeProdus);
            rs = findStatement.executeQuery();
            while(rs.next())
            {
                nume = rs.getString("Nume");
                if ( nume.contentEquals(numeProdus))
                    exista = 1;
                break;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProdusQueries:findByName " + e.getMessage());
        } finally {
            DataBaseConnection.close(rs);
            DataBaseConnection.close(findStatement);
            DataBaseConnection.close(dbConnection);
        }
        return exista;
    }
    /**
     *  Se declara un obiect de tip "Produs" si un ArrayList de "Produs".
     *  Se declara un statement ce va reprezenta query-ul SQL din string-ul selectStatementString.
     *  Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     *  se va afisa mesajul aferent warning-ului.
     *  Obiectul de tip ResultSet va contine rezultatul query-ului, fiecare element al acestui ResultSet
     *  reprezentand un rand din tabel.Valorile fiecarei celule din acel tabel poate fi extras prin specificarea
     *  numelui coloanei asociate.Datele sunt astfel extrase, obiectul de tip "Produs" este format si
     *  adaugat in lista de produse.
     *  Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     *  a statemenet-ului, a resultSet-ului si a conexiunii.
     *
     *
     * @return - se va returna lista de produse formata
     */
    public static ArrayList<Produs> select() {
        Produs produs = null;
        ArrayList<Produs> listaProduse = new ArrayList<Produs>();
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        try {
            selectStatement = dbConnection.prepareStatement(selectStatementString);
            rs = selectStatement.executeQuery();
            while(rs.next()) {

                String nume = rs.getString("Nume");
                float pret = rs.getFloat("Pret");

                produs = new Produs(nume, pret);
                listaProduse.add(produs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProdusQueries:select all " + e.getMessage());
        } finally {
            DataBaseConnection.close(rs);
            DataBaseConnection.close(selectStatement);
            DataBaseConnection.close(dbConnection);
        }
        return listaProduse;
    }

    /**
     * Se declara un statement ce va reprezenta query-ul SQL din string-ul deleteStatementString.
     * '?' va fi inlocuit cu parametrul "nume" dat metodei.
     * Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     * se va afisa mesajul aferent warning-ului.
     * Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     * a statemenet-ului si a conexiunii.
     * @param nume - numele produsului care se doreste a fi sters
     *
     */
    public static int delete(String nume) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement deleteStatement = null;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, nume);

            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProdusQueries:delete" + e.getMessage());
        } finally {
            DataBaseConnection.close(deleteStatement);
            DataBaseConnection.close(dbConnection);
        }
        return 0;
    }

    /**
     * Se declara un statement ce va reprezenta query-ul SQL din string-ul insertStatementString.
     * Simbolurile '?' vor fi inlocuite cu variabilele instanta ale obiectului de tip "Produs".
     * Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     * se va afisa mesajul aferent warning-ului.
     * Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     * a statemenet-ului si a conexiunii.
     * @param produs - un obiect de tip Produs
     *
     */
    public static int insert(Produs produs) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement insertStatement = null;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, produs.getNume());
            insertStatement.setFloat(2, produs.getPret());
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProdusQueries:insert " + e.getMessage());
        } finally {
            DataBaseConnection.close(insertStatement);
            DataBaseConnection.close(dbConnection);
        }
        return 0;
    }
}
