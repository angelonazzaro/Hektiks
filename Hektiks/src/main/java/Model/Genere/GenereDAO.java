package Model.Genere;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GenereDAO extends SQLDAO implements DAO<GenereDAO> {

    public GenereDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<GenereDAO> doRetrieveByCondition(String condition) throws SQLException {
        return null;
    }

    @Override
    public List<GenereDAO> doRetrieveAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<GenereDAO> doRetrieveByKey(GenereDAO obj) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean doSave(GenereDAO obj) throws SQLException {
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
