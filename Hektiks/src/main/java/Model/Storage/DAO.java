package Model.Storage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DAO<T> {

    List<T> doRetrieveAll(int start, int end) throws SQLException;

//    List<T> doRetrieveAll() throws SQLException;

    Optional<T> doRetrieve(T obj) throws SQLException;

    boolean doSave(T obj) throws SQLException;

    boolean doUpdate(Map<String, ?> values, String condition) throws SQLException;

    boolean doDelete(String condition) throws SQLException;
}
