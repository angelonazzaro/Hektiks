package Model.Sconto;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.SCONTI;

public class ScontoDAO extends SQLDAO implements DAO<Sconto> {

    public ScontoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Sconto> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(SCONTI, condition, new ScontoExtractor(), this.source);
    }

    @Override
    public Sconto doRetrieveByKey(Object... key) throws SQLException {

        if (key == null || key.length != 2)
            throw new InvalidPrimaryKeyException();

        List<Sconto> sconto = doRetrieveByCondition(
                String.format("%s.codice_sconto = '%s' AND %s.codice_gioco = '%s'",
                        SCONTI, key[0], SCONTI, key[1]));
        return sconto.isEmpty() ? null : sconto.get(0);
    }

    @Override
    public List<Sconto> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Sconto obj) throws SQLException {

        return genericDoSave(SCONTI, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(SCONTI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Sconto obj) throws SQLException {

        if (doRetrieveByKey(obj.getCodice_sconto(), obj.getCodice_gioco()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.codice_sconto = '%s' AND %s.codice_gioco = '%s'",
                    SCONTI, obj.getCodice_sconto(), SCONTI, obj.getCodice_gioco()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(SCONTI, condition, this.source);
    }
}
