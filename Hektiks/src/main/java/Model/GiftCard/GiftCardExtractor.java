package Model.GiftCard;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.GIFTCARDS;

public class GiftCardExtractor implements ResultSetExtractor<GiftCard> {

    @Override
    public GiftCard extract(ResultSet resultSet) throws SQLException {

        GiftCard giftCard = new GiftCard();

        giftCard.setCodice_giftCard(resultSet.getString(GIFTCARDS + ".codice_giftCard"));
        giftCard.setEmail_utente(resultSet.getString(GIFTCARDS + ".email_utente"));
        giftCard.setImporto(resultSet.getDouble(GIFTCARDS + ".importo"));
        giftCard.setData_ora_creazione(resultSet.getTimestamp(GIFTCARDS + ".data_ora_creazione"));
        giftCard.setData_ora_utilizzo(resultSet.getTimestamp(GIFTCARDS + ".data_ora_utilizzo"));

        return giftCard;
    }
}
