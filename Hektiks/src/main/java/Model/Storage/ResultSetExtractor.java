package Model.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract Result set into a Bean
 *
 * @param <B> custom class to convert
 */

public interface ResultSetExtractor<B> {

    /**
     * Extract info from a result set
     *
     * @param resultSet the set
     * @return the bean
     * @throws SQLException some error occurs
     */

    B extract(ResultSet resultSet) throws SQLException;
}
