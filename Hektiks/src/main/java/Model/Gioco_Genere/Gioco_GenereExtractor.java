package Model.Gioco_Genere;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.GIOCHI_GENERE;

public class Gioco_GenereExtractor implements ResultSetExtractor<Gioco_Genere> {

    @Override
    public Gioco_Genere extract(ResultSet resultSet) throws SQLException {

        Gioco_Genere gio_ge = new Gioco_Genere();

        gio_ge.setCodice_gioco(resultSet.getString(GIOCHI_GENERE + ".codice_gioco"));
        gio_ge.setNome_genere(resultSet.getString(GIOCHI_GENERE + ".nome_genere"));

        return gio_ge;
    }
}
