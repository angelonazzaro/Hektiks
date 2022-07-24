package Model.Storage;

import Utils.InvalidPrimaryKeyException;
import Utils.Logger.Logger;
import Utils.QueryBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public interface DAO<T> {

    /**
    * Effettua il retrieve applicando una condizione alla clausola where
    **/

    List<T> doRetrieveByCondition(String condition) throws SQLException;

    /**
     * Effettua il retrieve facendo una inner/outer join e applica una condizione alla clausola where
     **/

    List<T> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException;

    /**
     * Effettua il retrieve di tutti i record che matchano la key
     * Object... key -> String... key questo perchè nel db abbiamo tutte
     * primary key di tipo char/varchar/date/datetime
     **/

    T doRetrieveByKey(String... key) throws SQLException, InvalidPrimaryKeyException;

    /**
     * Efffettua il retrieve di tutti i record
     **/

    List<T> doRetrieveAll() throws SQLException;

    /**
     * Efffettua il retrieve di tutti i record applicando una LIMIT row_count
     **/

    List<T> doRetrieveAll(int row_count) throws SQLException;

    /**
     * Efffettua il retrieve di tutti i record applicando una LIMIT offset, row_count
     **/

    List<T> doRetrieveAll(int offset, int row_count) throws SQLException;

    /**
     * Salva l'oggetto in database
     **/

    boolean doSave(T obj) throws SQLException;

    /**
     * Aggiorna un i campi specificati nella Map nel database applicando una condizione alla clausola where
     **/

    boolean doUpdate(Map<String, ?> values, String condition) throws SQLException;

    /**
     * Salva l'oggetto in database se non è presente, lo aggiorna se è presente
     **/

    boolean doSaveOrUpdate(T obj) throws SQLException;

    /**
     * Elimina un record nel database applicando una condizione alla clausola where
     **/

    boolean doDelete(String condition) throws SQLException;

    /**
     * Eseguo la query e tramite un generico extractor estraggo i dati dal ResultSet
     * e li salvo in una lista di oggetti di tipo T
     **/

    default <E extends ResultSetExtractor<T>> List<T> genericDoRetrieveByCondition(String table, String condition, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(table).WHERE(condition).toString();

            Logger.consoleLog(Logger.QUERY, "[GENERIC-DO-RETRIEVE-BY-CONDITION] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }

    /**
     * Eseguo la query e tramite un generico extractor estraggo i dati dal ResultSet
     * e li salvo in una lista di oggetti di tipo T
     * Applico la join condition alla query
     **/

    default <E extends ResultSetExtractor<T>> List<T> genericDoRetrieveByJoin(String table, String joinType, String joinCondition, String condition, E extractor, DataSource source, String... tables) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query;

            if (joinType.toUpperCase(Locale.ROOT).equals("LEFT"))
                query = QueryBuilder.SELECT("*").FROM(table).LEFT_JOIN(joinCondition).WHERE(condition).toString();
            else if (joinType.toUpperCase(Locale.ROOT).equals("RIGHT"))
                query = QueryBuilder.SELECT("*").FROM(table).RIGHT_JOIN(joinCondition).WHERE(condition).toString();
            else
                query = QueryBuilder.SELECT("*").FROM(table).JOIN(joinCondition).WHERE(condition).toString();

            Logger.consoleLog(Logger.QUERY, "[GENERIC-DO-RETRIEVE-BY-JOIN] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set, tables));
                }
            }
        }
        return entity;
    }

    /**
     * Eseguo il metodo executeUpdate per salvare l'oggetto in database (solo i field
     * contenuti nella Map)
     * e verifico se è andato a buon fine restituendo rows == 1
     **/

    default boolean genericDoSave(String table, HashMap<String, ?> map, DataSource source) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.INSERT_INTO(table, map).toString();

            Logger.consoleLog(Logger.QUERY, "[GENERIC-DO-SAVE] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows == 1;
    }

    /**
     * Eseguo il metodo executeUpdate per aggiornare l'oggetto in database (solo i field
     * contenuti nella Map)
     * e verifico se è andato a buon fine restituendo rows > 0
     **/

    default boolean genericDoUpdate(String table, String condition, Map<String, ?> values, DataSource source) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.UPDATE(table).SET(values).WHERE(condition).toString();

            Logger.consoleLog(Logger.QUERY, "[GENERIC-DO-UPDATE] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows > 0;
    }

    /**
     * Eseguo il metodo executeUpdate per eliminare l'oggetto in database
     * e verifico se è andato a buon fine restituendo rows > 0
     * prima di eseguire la query disabilito i check sulla foreign key
     * e li riabilito dopo aver cancellato
     * nota: i vincoli dopo la query sono ancora rispettati e non vengono create
     * condizioni di inconsistenza dei dati
     **/

    default boolean genericDoDelete(String table, String condition, DataSource source) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.DELETE_FROM(table).WHERE(condition).toString();

            Logger.consoleLog(Logger.QUERY, "[GENERIC-DO-DELETE] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.execute("SET FOREIGN_KEY_CHECKS = 0;");
                rows = ps.executeUpdate();
                ps.execute("SET FOREIGN_KEY_CHECKS = 1;");
            }
        }
        return rows > 0;
    }
}
