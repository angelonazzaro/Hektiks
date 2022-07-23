package Model.Gioco_Genere;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.GIOCHI_GENERE;

public class Gioco_GenereExtractor implements ResultSetExtractor<Gioco_Genere> {

    @Override
    public Gioco_Genere extract(ResultSet resultSet, String... tables) throws SQLException {

        Gioco_Genere gioco_genere = new Gioco_Genere();

        gioco_genere.setCodice_gioco(resultSet.getString(GIOCHI_GENERE + ".codice_gioco"));
        gioco_genere.setNome_genere(resultSet.getString(GIOCHI_GENERE + ".nome_genere"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */

        if (tables.length > 0) {
            gioco_genere.setJoin(new ArrayList<>());
            for (String table : tables)
                gioco_genere.addToJoin(findExtractor(table).extract(resultSet));
        }

        return gioco_genere;
    }
}

