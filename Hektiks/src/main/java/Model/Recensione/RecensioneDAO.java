package Model.Recensione;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.RECENSIONI;

public class RecensioneDAO extends SQLDAO implements DAO<Recensione> {

    public RecensioneDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Recensione> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(RECENSIONI, condition, new RecensioneExtractor(), this.source);
    }

    @Override
    public List<Recensione> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(RECENSIONI, joinType, joinCondition, condition, new RecensioneExtractor(), this.source, tables);
    }

    @Override
    public Recensione doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 2)
            throw new InvalidPrimaryKeyException();

        List<Recensione> recensione = doRetrieveByCondition(
                String.format("%s.email_utente = '%s' AND %s.codice_gioco = '%s'",
                        RECENSIONI, key[0], RECENSIONI, key[1]));
        return recensione.isEmpty() ? null : recensione.get(0);
    }

    @Override
    public List<Recensione> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<Recensione> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<Recensione> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(Recensione obj) throws SQLException {

        return genericDoSave(RECENSIONI, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(RECENSIONI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Recensione obj) throws SQLException {

        if (doRetrieveByKey(obj.getEmail_utente(), obj.getCodice_gioco(), obj.getData_ora_pubblicazione().toString()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.email_utente = '%s' AND %s.data_creazione = '%s' AND %s.data_ora_pubblicazione = '%s'",
                        RECENSIONI, obj.getEmail_utente(), RECENSIONI, obj.getCodice_gioco(), RECENSIONI, obj.getData_ora_pubblicazione().toString()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(RECENSIONI, condition, this.source);
    }
}
