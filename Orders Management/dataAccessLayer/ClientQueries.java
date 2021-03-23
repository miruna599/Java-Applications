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

/**
 * "ClientQueries" este clasa ce contine metodele reprezentative operatiilor SQL pentru
 * baza de date. In fiecare metoda se va face conexiunea la baza de date.
 */
public class ClientQueries {

    protected static final Logger LOGGER = Logger.getLogger(ClientQueries.class.getName());
    private static final String insertStatementString = "INSERT INTO client (id,nume,adresa)" + " VALUES (?,?,?)";
    private static final String deleteStatementString = "DELETE FROM client WHERE nume=?";
    private static final String selectStatementString = "SELECT * FROM client ";

    /**
     * Se declara un statement ce va reprezenta query-ul SQL din string-ul deleteStatementString.
     * '?' va fi inlocuit cu parametrul "nume" dat metodei.
     * Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     * se va afisa mesajul aferent warning-ului.
     * Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     * a statemenet-ului si a conexiunii.
     * @param nume - numele clientului care se doreste a fi sters
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
            LOGGER.log(Level.WARNING, "ClientQueries:delete" + e.getMessage());
        } finally {
            DataBaseConnection.close(deleteStatement);
            DataBaseConnection.close(dbConnection);
        }
        return 0;
    }

    /**
     *  Se declara un obiect de tip "Client" si un ArrayList de "Client".
     *  Se declara un statement ce va reprezenta query-ul SQL din string-ul selectStatementString.
     *  Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     *  se va afisa mesajul aferent warning-ului.
     *  Obiectul de tip ResultSet va contine rezultatul query-ului, fiecare element al acestui ResultSet
     *  reprezentand un rand din tabel.Valorile fiecarei celule din acel tabel poate fi extras prin specificarea
     *  numelui coloanei asociate.Datele sunt astfel extrase, obiectul de tip "Client" este format si
     *  adaugat in lista de clienti.
     *  Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     *  a statemenet-ului, a resultSet-ului si a conexiunii.
     *
     *
     * @return - se va returna lista de clienti formata
     */

    public static ArrayList<Client> select() {
        Client client = null;
        ArrayList<Client> listaClienti = new ArrayList<Client>();
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        try {
            selectStatement = dbConnection.prepareStatement(selectStatementString);
            rs = selectStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("Id");
                String nume = rs.getString("Nume");
                String adresa = rs.getString("Adresa");
                client = new Client(id, nume, adresa);
                listaClienti.add(client);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientQueries:select all " + e.getMessage());
        } finally {
            DataBaseConnection.close(rs);
            DataBaseConnection.close(selectStatement);
            DataBaseConnection.close(dbConnection);
        }
        return listaClienti;
    }

    /**
     * Se declara un statement ce va reprezenta query-ul SQL din string-ul insertStatementString.
     * Simbolurile '?' vor fi inlocuite cu variabilele instanta ale obiectului de tip "Client".
     * Se va realiza instructiunea prin apelerea metodei "executeUpdate", iar in caz de Warning,
     * se va afisa mesajul aferent warning-ului.
     * Dupa efectuarea operatiei, se vor apela metodele din clasa DataBaseConnection de inchidere
     * a statemenet-ului si a conexiunii.
     * @param client - un obiect de tip Client
     *
     */

    public static int insert(Client client) {
        Connection dbConnection = DataBaseConnection.getConnection();
        PreparedStatement insertStatement = null;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, client.getId());
            insertStatement.setString(2, client.getNume());
            insertStatement.setString(3, client.getAdresa());
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientQueries:insert " + e.getMessage());
        } finally {
            DataBaseConnection.close(insertStatement);
            DataBaseConnection.close(dbConnection);
        }
        return 0;
    }


}
