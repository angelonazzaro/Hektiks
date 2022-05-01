package Model.Prodotto_Ordine;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Prodotto_OrdineDAO extends SQLDAO implements DAO<Prodotto_Ordine> {

    public Prodotto_OrdineDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Prodotto_Ordine> doRetrieveByCondition(String condition) throws SQLException {
        return null;
    }

    @Override
    public List<Prodotto_Ordine> doRetrieveAll() throws SQLException {
        return null;
    }

    /*@Override
    public Optional<Prodotto_Ordine> doRetrieveByKey(Prodotto_Ordine obj) throws SQLException {
        return Optional.empty();
    }*/

    @Override
    public boolean doSave(Prodotto_Ordine obj) throws SQLException {
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
