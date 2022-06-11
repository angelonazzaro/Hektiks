package Model.Utente;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.UTENTI;

public class UtenteDAO extends SQLDAO implements DAO<Utente> {

    public UtenteDAO(DataSource source) {
        super(source);
    }

    public List<Utente> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(UTENTI, condition, new UtenteExtractor(), this.source);
    }

    @Override
    public List<Utente> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(UTENTI, joinType, joinCondition, condition, new UtenteExtractor(), this.source, tables);
    }

    @Override
    public Utente doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 1)
            throw new InvalidPrimaryKeyException();

        List<Utente> utente = doRetrieveByCondition(
                String.format("%s.email = '%s'", UTENTI, key[0]));
        return utente.isEmpty() ? null : utente.get(0);
    }

    @Override
    public List<Utente> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<Utente> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<Utente> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(Utente obj) throws SQLException {

        return genericDoSave(UTENTI, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(UTENTI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Utente obj) throws SQLException {

        if (doRetrieveByKey(obj.getEmail()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.email = '%s'", UTENTI, obj.getEmail()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(UTENTI, condition, this.source);
    }
}
