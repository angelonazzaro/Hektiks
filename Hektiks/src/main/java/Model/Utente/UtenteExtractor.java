package Model.Utente;

import Model.Storage.ResultSetExtractor;

import static Model.Storage.Entities.UTENTI;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UtenteExtractor implements ResultSetExtractor<Utente> {

    @Override
    public Utente extract(ResultSet resultSet) throws SQLException {

        return new Utente(resultSet.getString(UTENTI + ".email"),
                resultSet.getString(UTENTI + ".username"),
                resultSet.getString(UTENTI + ".password"),
                resultSet.getDate(UTENTI + ".data_registrazione"),
                resultSet.getBoolean(UTENTI + ".ruolo"),
                resultSet.getDouble(UTENTI + ".saldo"),
                resultSet.getString(UTENTI + ".biografia"));
    }
}
