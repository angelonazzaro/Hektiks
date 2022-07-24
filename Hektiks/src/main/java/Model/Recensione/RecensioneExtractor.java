package Model.Recensione;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.RECENSIONI;

public class RecensioneExtractor implements ResultSetExtractor<Recensione> {

    @Override
    public Recensione extract(ResultSet resultSet, String... tables) throws SQLException {

        Recensione recensione = new Recensione();

        recensione.setEmail_utente(resultSet.getString(RECENSIONI + ".email_utente"));
        recensione.setCodice_gioco(resultSet.getString(RECENSIONI + ".codice_gioco"));
        recensione.setData_ora_pubblicazione(resultSet.getTimestamp(RECENSIONI + ".data_ora_pubblicazione"));
        recensione.setVoto(resultSet.getDouble(RECENSIONI + ".voto"));
        recensione.setDescrizione(resultSet.getString(RECENSIONI + ".descrizione"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */

        if (tables.length > 0) {
            recensione.setJoin(new ArrayList<>());
            for (String table : tables)
                recensione.addToJoin(findExtractor(table).extract(resultSet));
        }

        return recensione;
    }
}
