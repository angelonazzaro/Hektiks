package Model.Utente;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

import static Model.Storage.Entities.UTENTI;

public class UtenteDAO extends SQLDAO implements DAO<Utente> {

    public UtenteDAO(DataSource source) {
        super(source);
    }

    public List<Utente> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(UTENTI, condition, new UtenteExtractor(), this.source);
    }

    @Override
    public Utente doRetrieveByKey(Object... key) throws SQLException {

        List<Utente> utente = doRetrieveByCondition(UTENTI + ".email = " + "'" + key.toString() + "'");
        return utente.isEmpty() ? null : utente.get(0);
    }

    @Override
    public List<Utente> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Utente obj) throws SQLException {

        return genericDoSave(UTENTI, new HashMap<>() {{
                    put("email", obj.getEmail());
                    put("nome", obj.getNome());
                    put("cognome", obj.getCognome());
                    put("username", obj.getUsername());
                    put("password_utente", obj.getPassword_utente());
                    put("data_registrazione", obj.getData_registrazione().toString());
                    put("ruolo", obj.isRuolo());
                    put("saldo", obj.getSaldo());
                    put("biografia", obj.getBiografia());
                }},
                this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(UTENTI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Utente obj) throws SQLException {

        if (doRetrieveByKey(obj.getEmail()) == null)
            return doSave(obj);

        return doUpdate(new HashMap<>() {{
            put("email", obj.getEmail());
            put("nome", obj.getNome());
            put("cognome", obj.getCognome());
            put("username", obj.getUsername());
            put("password_utente", obj.getPassword_utente());
            put("data_registrazione", obj.getData_registrazione().toString());
            put("ruolo", obj.isRuolo());
            put("saldo", obj.getSaldo());
            put("biografia", obj.getBiografia());

        }}, UTENTI + ".email = " + "'" + obj.getEmail() + "'");
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(UTENTI, condition, this.source);
    }
}
