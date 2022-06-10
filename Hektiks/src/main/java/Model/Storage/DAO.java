package Model.Storage;

import Utils.InvalidPrimaryKeyException;
import Utils.QueryBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface DAO<T> {

    List<T> doRetrieveByCondition(String condition) throws SQLException;

    List<T> doRetrieveByJoin(String joinTable, String join, String predicate, String condition) throws SQLException;

    List<T> doRetrieveByJoin(String joinTable, String join, String predicate, String condition, int row_count) throws SQLException;
    /*
    * Object... key -> String... key questo perchè nel db abbiamo tutte
    * primary key di tipo char/varchar/date/datetime
    */
    T doRetrieveByKey(String... key) throws SQLException, InvalidPrimaryKeyException;

    List<T> doRetrieveAll() throws SQLException;

    List<T> doRetrieveAll(int row_count) throws SQLException;

    List<T> doRetrieveAll(int offset, int row_count) throws SQLException;

    boolean doSave(T obj) throws SQLException;

    boolean doUpdate(Map<String, ?> values, String condition) throws SQLException;

    boolean doSaveOrUpdate(T obj) throws SQLException;

    boolean doDelete(String condition) throws SQLException;

    default <E extends ResultSetExtractor<T>> List<T> genericDoRetrieveByCondition(String table, String condition, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<T>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(table).WHERE(condition).toString();

            System.out.print("[GENERIC-DO-RETRIEVE-BY-CONDITION] ");
            System.out.println(query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }

    default <E extends ResultSetExtractor<T>> List<T> genericDoRetrieveByJoin(String table, String joinTable, String join, String predicate,  String condition, E extractor, DataSource source) throws SQLException {
        final List<T> entity = new ArrayList<T>();

        try (Connection conn = source.getConnection()) {

            String query = null;
            if (join.equals("left"))
                query = QueryBuilder.SELECT("*").FROM(table).LEFT_JOIN(joinTable).ON(predicate).WHERE(condition).toString();
            else if (join.equals("right"))
                query = QueryBuilder.SELECT("*").FROM(table).RIGHT_JOIN(joinTable).ON(predicate).WHERE(condition).toString();
            else
                query = QueryBuilder.SELECT("*").FROM(table).JOIN(joinTable).ON(predicate).WHERE(condition).toString();

            System.out.print("[GENERIC-DO-RETRIEVE-BY-JOIN] ");
            System.out.println(query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }

    default boolean genericDoSave(String table, HashMap<String, ?> map, DataSource source) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.INSERT_INTO(table, map).toString();

            System.out.print("[GENERIC-DO-SAVE] ");
            System.out.println(query);
            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows == 1;
    }

    default boolean genericDoUpdate(String table, String condition, Map<String, ?> values, DataSource source) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.UPDATE(table).SET(values).WHERE(condition).toString();

            System.out.print("[GENERIC-DO-UPDATE] ");
            System.out.println(query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows > 0;
    }

    default boolean genericDoDelete(String table, String condition, DataSource source) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.DELETE_FROM(table).WHERE(condition).toString();

            System.out.print("[GENERIC-DO-DELETE] ");
            System.out.println(query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows > 0;
    }
}
