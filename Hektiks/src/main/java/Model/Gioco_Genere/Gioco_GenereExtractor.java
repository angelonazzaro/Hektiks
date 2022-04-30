package Model.Gioco_Genere;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.GIOCHI_GENERE;

public class Gioco_GenereExtractor implements ResultSetExtractor<Gioco_Genere> {

    @Override
    public Gioco_Genere extract(ResultSet resultSet) throws SQLException {

        Gioco_Genere gioco_genere = new Gioco_Genere();

        gioco_genere.setCodice_gioco(resultSet.getString(GIOCHI_GENERE + ".codice_gioco"));
        gioco_genere.setNome_genere(resultSet.getString(GIOCHI_GENERE + ".nome_genere"));

        return gioco_genere;
    }
}
