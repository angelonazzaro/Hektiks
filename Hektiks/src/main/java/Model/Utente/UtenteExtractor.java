package Model.Utente;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class UtenteExtractor implements ResultSetExtractor<Utente> {


    @Override
    public Utente extract(ResultSet resultSet) throws SQLException {

        return new Utente(resultSet.getString("Utenti.email"),
                resultSet.getString("Utenti.username"),
                resultSet.getString("Utenti.password"),
                resultSet.getDate("Utenti.data_registrazione"),
                resultSet.getBoolean("Utenti.ruolo"),
                resultSet.getDouble("Utenti.saldo"),
                resultSet.getString("Utenti.biografia"));
    }
}
