package Model.Utente;

import Model.Storage.ResultSetExtractor;

import static Model.Storage.Entities.UTENTI;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UtenteExtractor implements ResultSetExtractor<Utente> {

    @Override
    public Utente extract(ResultSet resultSet) throws SQLException {

        Utente utente = new Utente();

        utente.setEmail(resultSet.getString(UTENTI + ".email"));
        utente.setNome(resultSet.getString(UTENTI + ".nome"));
        utente.setCognome(resultSet.getString(UTENTI + ".cognome"));
        utente.setPassword_utente(resultSet.getString(UTENTI + ".username"));
        utente.setPassword_utente(resultSet.getString(UTENTI + ".password_utente"));
        utente.setData_registrazione(resultSet.getDate(UTENTI + ".data_registrazione"));
        utente.setRuolo(resultSet.getBoolean(UTENTI + ".ruolo"));
        utente.setSaldo(resultSet.getDouble(UTENTI + ".saldo"));
        utente.setBiografia(resultSet.getString(UTENTI + ".biografia"));

        return utente;
    }
}
