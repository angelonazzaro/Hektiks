package Model.GiftCard;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.GIFTCARDS;

public class GiftCardExtractor implements ResultSetExtractor<GiftCard> {

    @Override
    public GiftCard extract(ResultSet resultSet, String... tables) throws SQLException {

        GiftCard giftCard = new GiftCard();

        giftCard.setCodice_giftCard(resultSet.getString(GIFTCARDS + ".codice_giftCard"));
        giftCard.setEmail_utente(resultSet.getString(GIFTCARDS + ".email_utente"));
        giftCard.setImporto(resultSet.getDouble(GIFTCARDS + ".importo"));
        giftCard.setData_ora_creazione(resultSet.getTimestamp(GIFTCARDS + ".data_ora_creazione"));
        giftCard.setData_ora_utilizzo(resultSet.getTimestamp(GIFTCARDS + ".data_ora_utilizzo"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */

        if (tables.length > 0) {
            giftCard.setJoin(new ArrayList<>());
            for (String table : tables)
                giftCard.addToJoin(findExtractor(table).extract(resultSet));
        }

        return giftCard;
    }
}
