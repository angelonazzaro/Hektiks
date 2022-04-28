package Model.Genere;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.GENERI;

public class GenereExtractor implements ResultSetExtractor<Genere> {

    @Override
    public Genere extract(ResultSet resultSet) throws SQLException {

        Genere gen = new Genere();
        gen.setNome_genere(resultSet.getString(GENERI + ".nome_genere"));

        return gen;
    }
}
