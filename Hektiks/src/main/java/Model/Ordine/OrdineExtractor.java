package Model.Ordine;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.ORDINI;

public class OrdineExtractor implements ResultSetExtractor<Ordine> {

    @Override
    public Ordine extract(ResultSet resultSet, String... tables) throws SQLException {

        Ordine ordine = new Ordine();

        ordine.setEmail_utente(resultSet.getString(ORDINI + ".email_utente"));
        ordine.setCodice_ordine(resultSet.getString(ORDINI + ".codice_ordine"));
        ordine.setData_ora_ordinazione(resultSet.getTimestamp(ORDINI + ".data_ora_ordinazione"));
        ordine.setPrezzo_totale(resultSet.getDouble(ORDINI + ".prezzo_totale"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */

        if (tables.length > 0) {
            ordine.setJoin(new ArrayList<>());
            for (String table : tables)
                ordine.addToJoin(findExtractor(table).extract(resultSet));
        }

        return ordine;
    }
}
