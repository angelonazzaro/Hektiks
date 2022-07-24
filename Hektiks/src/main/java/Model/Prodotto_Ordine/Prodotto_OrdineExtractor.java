package Model.Prodotto_Ordine;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.PRODOTTI_ORDINI;

public class Prodotto_OrdineExtractor implements ResultSetExtractor<Prodotto_Ordine> {

    @Override
    public Prodotto_Ordine extract(ResultSet resultSet, String... tables) throws SQLException {

        Prodotto_Ordine prodotto_ordine = new Prodotto_Ordine();

        prodotto_ordine.setEmail_utente(resultSet.getString(PRODOTTI_ORDINI + ".email_utente"));
        prodotto_ordine.setCodice_ordine(resultSet.getString(PRODOTTI_ORDINI + ".codice_ordine"));
        prodotto_ordine.setCodice_gioco(resultSet.getString(PRODOTTI_ORDINI + ".codice_gioco"));
        prodotto_ordine.setData_ora_creazione(resultSet.getTimestamp(PRODOTTI_ORDINI + ".data_ora_creazione"));
        prodotto_ordine.setPrezzo(resultSet.getDouble(PRODOTTI_ORDINI + ".prezzo"));
        prodotto_ordine.setQuantita(resultSet.getInt(PRODOTTI_ORDINI + ".quantita"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */

        if (tables.length > 0) {
            prodotto_ordine.setJoin(new ArrayList<>());
            for (String table : tables)
                prodotto_ordine.addToJoin(findExtractor(table).extract(resultSet));
        }

        return prodotto_ordine;
    }
}
