package Model.GiftCard;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public GiftCard doRetrieveByKey(Object... key) throws SQLException {

        List<GiftCard> giftCard = doRetrieveByCondition(GIFTCARDS + ".codice_giftCard = " + "'" + key.toString() + "'");
        return giftCard.isEmpty() ? null : giftCard.get(0);
    }

    @Override
    public List<GiftCard> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(GiftCard obj) throws SQLException {

        return genericDoSave(GIFTCARDS, new HashMap<>() {{

            put("codice_giftcard", obj.getCodice_giftCard());
            put("email_utente", obj.getEmail_utente());
            put("importo", obj.getImporto());
            put("data_ora_creazione", obj.getData_ora_creazione().toString());
            put("data_ora_utilizzo", obj.getData_ora_utilizzo().toString());

        }}, this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(GIFTCARDS, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(GiftCard obj) throws SQLException {

        if (doRetrieveByKey(obj.getCodice_giftCard()) == null)
            return doSave(obj);

        return doUpdate(new HashMap<>() {{
            put("codice_giftcard", obj.getCodice_giftCard());
            put("email_utente", obj.getEmail_utente());
            put("importo", obj.getImporto());
            put("data_ora_creazione", obj.getData_ora_creazione().toString());
            put("data_ora_utilizzo", obj.getData_ora_utilizzo().toString());

        }}, GIFTCARDS + ".codice_giftCard = " + "'" + obj.getCodice_giftCard() + "'");
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(GIFTCARDS, condition, this.source);
    }
}
