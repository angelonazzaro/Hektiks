package Model.Utente;

import Model.Storage.DAO;
import Utils.QueryBuilder;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static Model.Storage.Entities.UTENTI;

public class UtenteDAO extends SQLDAO implements DAO<Utente> {


    public UtenteDAO(DataSource source) {
        super(source);
    }

    public List<Utente> doRetrieveByCondition(String condition) throws SQLException {

        final List<Utente> utenti = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(UTENTI).WHERE(condition).toString();

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();
                UtenteExtractor utenteExtractor = new UtenteExtractor();

                while (set.next()) {
                    utenti.add(utenteExtractor.extract(set));
                }
            }
        }
        return utenti;
    }

//    @Override
//    public List<Utente> doRetrieveAll(int start, int end) throws SQLException {
//
//        final List<Utente> utenti = new ArrayList<>();
//
//        try (Connection conn = source.getConnection()) {
//
//            String query = QueryBuilder.SELECT("*").FROM(UTENTI).LIMIT(start, end).toString();
//
//            try (PreparedStatement ps = conn.prepareStatement(query)) {
//                ResultSet set = ps.executeQuery();
//                UtenteExtractor utenteExtractor = new UtenteExtractor();
//
//                while (set.next()) {
//
//                    utenti.add(utenteExtractor.extract(set));
//                }
//            }
//        }
//        return utenti;
//    }

    @Override
    public List<Utente> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public Optional<Utente> doRetrieveByKey(Utente key) throws SQLException {

        Utente utente = null;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(UTENTI).WHERE(UTENTI + ".email = ?").toString();

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, key.getEmail());
                ResultSet set = ps.executeQuery();

                if (set.next()) {

                    utente = new UtenteExtractor().extract(set);
                }
            }
        }

        return Optional.ofNullable(utente);
    }


    @Override
    public boolean doSave(Utente obj) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.INSERT_INTO(UTENTI, new HashMap<>() {{
                put("email", obj.getEmail());
                put("username", obj.getUsername());
                put("password", obj.getPassword_utente());
                put("data_registrazione", obj.getData_registrazione());
                put("ruolo", obj.isRuolo());
                put("saldo", obj.getSaldo());
                put("biografia", obj.getBiografia());
            }}).toString();

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows == 1;
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.UPDATE(UTENTI).SET(values).WHERE(condition).toString();

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows > 0;
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.DELETE_FROM(UTENTI).WHERE(condition).toString();

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows > 0;
    }


}
