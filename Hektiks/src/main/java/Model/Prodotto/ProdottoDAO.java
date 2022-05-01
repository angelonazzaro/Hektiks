package Model.Prodotto;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProdottoDAO extends SQLDAO implements DAO<Prodotto> {

    public ProdottoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Prodotto> doRetrieveByCondition(String condition) throws SQLException {
        return null;
    }

    @Override
    public List<Prodotto> doRetrieveAll() throws SQLException {
        return null;
    }

    /*@Override
    public Optional<Prodotto> doRetrieveByKey(Prodotto obj) throws SQLException {
        return Optional.empty();
    }*/

    @Override
    public boolean doSave(Prodotto obj) throws SQLException {
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
