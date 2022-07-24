package Model.GiftCard;

import Model.Genere.GenereExtractor;
import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.GENERI;
import static Model.Storage.Entities.GIFTCARDS;

public class GiftCardDAO extends SQLDAO implements DAO<GiftCard> {

    public GiftCardDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<GiftCard> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(GIFTCARDS, condition, new GiftCardExtractor(), this.source);
    }

    @Override
    public List<GiftCard> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(GIFTCARDS, joinType, joinCondition, condition, new GiftCardExtractor(), this.source);
    }


    @Override
    public GiftCard doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 1)
            throw new InvalidPrimaryKeyException();

        List<GiftCard> giftCard = doRetrieveByCondition(
                String.format("%s.codice_giftCard = '%s'", GIFTCARDS, key[0]));
        return giftCard.isEmpty() ? null : giftCard.get(0);
    }

    @Override
    public List<GiftCard> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<GiftCard> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<GiftCard> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(GiftCard obj) throws SQLException {

        return genericDoSave(GIFTCARDS, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(GIFTCARDS, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(GiftCard obj) throws SQLException {

        if (doRetrieveByKey(obj.getCodice_giftCard()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.codice_giftCard = '%s'", GIFTCARDS, obj.getCodice_giftCard()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(GIFTCARDS, condition, this.source);
    }
}
