package Model.Genere;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.GENERI;

public class GenereExtractor implements ResultSetExtractor<Genere> {

    @Override
    public Genere extract(ResultSet resultSet) throws SQLException {

        return new Genere(resultSet.getString(GENERI + ".nome_genere"));
    }
}
