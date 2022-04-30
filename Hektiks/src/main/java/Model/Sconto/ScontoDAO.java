package Model.Sconto;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ScontoDAO extends SQLDAO implements DAO<Sconto> {

    public ScontoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Sconto> doRetrieveByCondition(String condition) throws SQLException {
        return null;
    }

    @Override
    public List<Sconto> doRetrieveAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<Sconto> doRetrieveByKey(Sconto obj) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean doSave(Sconto obj) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {
        return false;
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return false;
    }
}
