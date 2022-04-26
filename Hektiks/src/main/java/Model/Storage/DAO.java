package Model.Storage;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    List<T> doRetrieveAll(int start, int end) throws SQLException;

    Optional<T> doRetrieve(T obj) throws SQLException;

    boolean doSave(T obj) throws SQLException;

    boolean doUpdate(T obj) throws SQLException;

    boolean doDelete(T obj) throws SQLException;
}
