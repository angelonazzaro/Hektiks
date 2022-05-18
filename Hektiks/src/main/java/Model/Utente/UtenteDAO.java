package Model.Utente;

import Model.Storage.DAO;
import Utils.QueryBuilder;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(condition, UTENTI, this.source);
    }
}
