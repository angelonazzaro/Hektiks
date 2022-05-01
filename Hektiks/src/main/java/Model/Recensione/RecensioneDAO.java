package Model.Recensione;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RecensioneDAO extends SQLDAO implements DAO<Recensione> {

    public RecensioneDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Recensione> doRetrieveByCondition(String condition) throws SQLException {
        return null;
    }

    @Override
    public List<Recensione> doRetrieveAll() throws SQLException {
        return null;
    }

    /*@Override
    public Optional<Recensione> doRetrieveByKey(Recensione obj) throws SQLException {
        return Optional.empty();
    }*/

    @Override
    public boolean doSave(Recensione obj) throws SQLException {
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
