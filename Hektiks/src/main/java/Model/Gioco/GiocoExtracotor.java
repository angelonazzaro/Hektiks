package Model.Gioco;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiocoExtracotor implements ResultSetExtractor<Gioco> {

    @Override
    public Gioco extract(ResultSet resultSet) throws SQLException {
        return null;
    }
}
