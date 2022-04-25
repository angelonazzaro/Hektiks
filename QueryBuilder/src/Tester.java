import javax.swing.*;
import java.sql.*;

public class Tester {

    private static Connection con;
    private static Statement stmt;

    private static void openConnection() {

        try {

            String username = "root";
            String password = "francesco.1";
            String url = "jdbc:mysql://localhost:3306/euroGames";

            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            closeConnection();
        }
    }

    private static void closeConnection() {

        try {
            con.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        } finally {
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws SQLException {

        openConnection();

        String sql = QueryBuilder.select("*")
                .from("utenti u").join("acquisti a").on("u.email = a.email_utente")
                .join("prodotti p").on("a.codice_prodotto = p.codice_prodotto")
                .where("p.tipo = \"Console\"")
                .orderBy("prezzo_base")
                .toString();


        stmt.executeQuery(sql);

        closeConnection();
    }
}
