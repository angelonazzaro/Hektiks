package Model.GiftCard;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GiftCardDAO extends SQLDAO implements DAO<GiftCard> {

    public GiftCardDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<GiftCard> doRetrieveByCondition(String condition) throws SQLException {
        return null;
    }

    @Override
    public List<GiftCard> doRetrieveAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<GiftCard> doRetrieveByKey(GiftCard obj) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean doSave(GiftCard obj) throws SQLException {
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
