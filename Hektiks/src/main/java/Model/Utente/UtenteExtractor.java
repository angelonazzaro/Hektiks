package Model.Utente;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.UTENTI;


public class UtenteExtractor implements ResultSetExtractor<Utente> {

    @Override
    public Utente extract(ResultSet resultSet, String... tables) throws SQLException {

        Utente utente = new Utente();

        utente.setEmail(resultSet.getString(UTENTI + ".email"));
        utente.setNome(resultSet.getString(UTENTI + ".nome"));
        utente.setCognome(resultSet.getString(UTENTI + ".cognome"));
        utente.setUsername(resultSet.getString(UTENTI + ".username"));
        utente.setPassword_utente(resultSet.getString(UTENTI + ".password_utente"));
        utente.setData_registrazione(resultSet.getDate(UTENTI + ".data_registrazione"));
        utente.setRuolo(resultSet.getBoolean(UTENTI + ".ruolo"));
        utente.setSaldo(resultSet.getDouble(UTENTI + ".saldo"));
        utente.setProfile_pic(resultSet.getString(UTENTI + ".profile_pic"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */

        if (tables.length > 0) {
            utente.setJoin(new ArrayList<>());
            for (String table : tables)
                utente.addToJoin(findExtractor(table).extract(resultSet));
        }

        return utente;
    }
}
