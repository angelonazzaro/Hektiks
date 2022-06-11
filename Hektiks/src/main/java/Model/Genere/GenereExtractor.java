package Model.Genere;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.GENERI;

public class GenereExtractor implements ResultSetExtractor<Genere> {

    @Override
    public Genere extract(ResultSet resultSet, String... tables) throws SQLException {

        Genere genere = new Genere();
        genere.setNome_genere(resultSet.getString(GENERI + ".nome_genere"));

        if (tables.length > 0) {
            genere.setJoin(new ArrayList<>());
            for (String table : tables) genere.addToJoin(findExtractor(table).extract(resultSet));
        }

        return genere;
    }
}
