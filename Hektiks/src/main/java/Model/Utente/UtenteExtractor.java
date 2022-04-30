package Model.Utente;

import Model.Storage.ResultSetExtractor;

import static Model.Storage.Entities.UTENTI;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UtenteExtractor implements ResultSetExtractor<Utente> {

    @Override
    public Utente extract(ResultSet resultSet) throws SQLException {

        Utente ute = new Utente();

        ute.setEmail(resultSet.getString(UTENTI + ".email"));
        ute.setNome(resultSet.getString(UTENTI + ".nome"));
        ute.setCognome(resultSet.getString(UTENTI + ".cognome"));
        ute.setPassword_utente(resultSet.getString(UTENTI + ".username"));
        ute.setPassword_utente(resultSet.getString(UTENTI + ".password"));
        ute.setData_registrazione(resultSet.getDate(UTENTI + ".data_registrazione"));
        ute.setRuolo(resultSet.getBoolean(UTENTI + ".ruolo"));
        ute.setSaldo(resultSet.getDouble(UTENTI + ".saldo"));
        ute.setBiografia(resultSet.getString(UTENTI + ".biografia"));

        return ute;
    }
}
