package Model.Carrello;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CarrelloDAO extends SQLDAO implements DAO<Carrello> {

    public CarrelloDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Carrello> doRetrieveByCondition(String condition) throws SQLException {
        return null;
    }

    @Override
    public List<Carrello> doRetrieveAll() throws SQLException {
        return null;
    }

    /*@Override
    public Optional<Carrello> doRetrieveByKey(Carrello obj) throws SQLException {
        return Optional.empty();
    }*/

    @Override
    public boolean doSave(Carrello obj) throws SQLException {
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
