package Model.Prodotto;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.PRODOTTI;

public class ProdottoExtractor implements ResultSetExtractor<Prodotto> {

    @Override
    public Prodotto extract(ResultSet resultSet, String... tables) throws SQLException {

        Prodotto prodotto = new Prodotto();

        prodotto.setEmail_utente(resultSet.getString(PRODOTTI + ".email_utente"));
        prodotto.setCodice_gioco(resultSet.getString(PRODOTTI + ".codice_gioco"));
        prodotto.setQuantita_disponibile(resultSet.getInt(PRODOTTI + ".quantita_disponibile"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */

        if (tables.length > 0) {
            prodotto.setJoin(new ArrayList<>());
            for (String table : tables)
                prodotto.addToJoin(findExtractor(table).extract(resultSet));
        }

        return prodotto;
    }
}
