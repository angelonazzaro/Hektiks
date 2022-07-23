package Model.Gioco;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.GIOCHI;

public class GiocoExtractor implements ResultSetExtractor<Gioco> {

    @Override
    public Gioco extract(ResultSet resultSet, String... tables) throws SQLException {

        Gioco gioco = new Gioco();

        gioco.setCodice_gioco(resultSet.getString(GIOCHI + ".codice_gioco"));
        gioco.setTitolo(resultSet.getString(GIOCHI + ".titolo"));
        gioco.setDescrizione(resultSet.getString(GIOCHI + ".descrizione"));
        gioco.setTrailer(resultSet.getString(GIOCHI + ".trailer"));
        gioco.setData_uscita(resultSet.getDate(GIOCHI + ".data_uscita"));
        gioco.setCopertina(resultSet.getString(GIOCHI + ".copertina"));
        gioco.setPrezzo(resultSet.getDouble(GIOCHI + ".prezzo"));
        gioco.setQuantita_disponibile(resultSet.getInt(GIOCHI + ".quantita_disponibile"));
        gioco.setNumero_vendite(resultSet.getInt(GIOCHI + ".numero_vendite"));
        gioco.setPercentuale_sconto(resultSet.getInt(GIOCHI + ".percentuale_sconto"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */

        if (tables.length > 0) {
            gioco.setJoin(new ArrayList<>());
            for (String table : tables)
                gioco.addToJoin(findExtractor(table).extract(resultSet));
        }

        return gioco;
    }
}
