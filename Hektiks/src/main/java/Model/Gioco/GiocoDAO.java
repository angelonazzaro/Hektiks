package Model.Gioco;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.GIOCHI;

public class GiocoDAO extends SQLDAO implements DAO<Gioco> {

    public GiocoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Gioco> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(GIOCHI, condition, new GiocoExtractor(), this.source);
    }

    @Override
    public List<Gioco> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(GIOCHI, joinType, joinCondition, condition, new GiocoExtractor(), this.source, tables);
    }

    @Override
    public Gioco doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 1)
            throw new InvalidPrimaryKeyException();

        List<Gioco> gioco = doRetrieveByCondition(
                String.format("%s.codice_gioco = '%s'", GIOCHI, key[0]));
        return gioco.isEmpty() ? null : gioco.get(0);
    }

    @Override
    public List<Gioco> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<Gioco> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<Gioco> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(Gioco obj) throws SQLException {

        return genericDoSave(GIOCHI, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(GIOCHI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Gioco obj) throws SQLException {

        if (doRetrieveByKey(obj.getCodice_gioco()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.codice_gioco = '%s'", GIOCHI, obj.getCodice_gioco()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(GIOCHI, condition, this.source);
    }
}
