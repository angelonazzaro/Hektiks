package Model.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor<B> {

    B extract(ResultSet resultSet) throws SQLException;
}
