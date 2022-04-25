import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * {@link Iterable} adapter for JDBC result sets.
 */

public class ResultSetAdapter implements Iterable<Map<String, Object>>, AutoCloseable {

    private final ResultSet resultSet;
    private final ResultSetMetaData resultSetMetaData;

    private final Iterator<Map<String, Object>> iterator = new Iterator<>() {

        private Boolean hasNext = null;

        @Override
        public boolean hasNext() {

            if (hasNext == null) {

                try {

                    hasNext = resultSet.next() ? Boolean.TRUE : Boolean.FALSE;
                } catch (SQLException exception) {

                    throw new RuntimeException(exception);
                }
            }

            return hasNext;
        }

        @Override
        public Map<String, Object> next() {

            if (!hasNext()) {

                throw new NoSuchElementException();
            }

            Map<String, Object> row = new LinkedHashMap<>();

            try {

                for (int i = 1, n = resultSetMetaData.getColumnCount(); i <= n; i++) {

                    row.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
                }
            } catch (SQLException exception) {

                throw new RuntimeException(exception);
            }

            hasNext = null;

            return row;
        }
    };

    /**
     * Constructs a new result set adapter.
     *
     * @param resultSet The source result set.
     */

    public ResultSetAdapter(ResultSet resultSet) {

        if (resultSet == null) {

            throw new IllegalArgumentException();
        }

        this.resultSet = resultSet;

        try {

            resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException exception) {

            throw new RuntimeException(exception);
        }
    }

    /**
     * Returns the result set's fetch size.
     *
     * @return The result set's fetch size.
     * @throws SQLException If an error occurs while retrieving the fetch size.
     */

    public int getFetchSize() throws SQLException {

        return resultSet.getFetchSize();
    }

    /**
     * Sets the result set's fetch size.
     *
     * @param fetchSize The result set's fetch size.
     * @throws SQLException If an error occurs while setting the fetch size.
     */

    public void setFetchSize(int fetchSize) throws SQLException {

        resultSet.setFetchSize(fetchSize);
    }

    /**
     * Returns the next result.
     *
     * @return The next result, or <code>null</code> if there are no more results.
     */

    public Map<String, Object> next() {

        return iterator.hasNext() ? iterator.next() : null;
    }

    @Override
    public Iterator<Map<String, Object>> iterator() {

        return iterator;
    }

    @Override
    public void close() throws SQLException {

        resultSet.close();
    }

    /**
     * Returns a stream over the results. Closing the returned stream does not
     * close the underlying result set.
     *
     * @return A stream over the results.
     */

    public Stream<Map<String, Object>> stream() {

        return StreamSupport.stream(spliterator(), false);
    }
}