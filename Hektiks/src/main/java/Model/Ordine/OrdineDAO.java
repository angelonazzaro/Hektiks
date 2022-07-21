package Model.Ordine;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.ORDINI;

public class OrdineDAO extends SQLDAO implements DAO<Ordine> {

    public OrdineDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Ordine> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(ORDINI, condition, new OrdineExtractor(), this.source);
    }

    @Override
    public List<Ordine> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(ORDINI, joinType, joinCondition, condition, new OrdineExtractor(), this.source, tables);
    }

    @Override
    public Ordine doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 2)
            throw new InvalidPrimaryKeyException();

        List<Ordine> ordine = doRetrieveByCondition(
                String.format("%s.email_utente = '%s' AND %s.codice_ordine = '%s'",
                        ORDINI, key[0], ORDINI, key[1]));
        return ordine.isEmpty() ? null : ordine.get(0);
    }

    @Override
    public List<Ordine> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<Ordine> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<Ordine> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(Ordine obj) throws SQLException {

        return genericDoSave(ORDINI, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(ORDINI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Ordine obj) throws SQLException {

        if (doRetrieveByKey(obj.getEmail_utente(), obj.getCodice_ordine()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.email_utente = '%s' AND %s.codice_ordine = '%s'",
                        ORDINI, obj.getEmail_utente(), ORDINI, obj.getCodice_ordine()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(ORDINI, condition, this.source);
    }
}
