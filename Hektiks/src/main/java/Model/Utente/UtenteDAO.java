package Model.Utente;

import Model.Storage.DAO;
import Model.Storage.QueryBuilder;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UtenteDAO extends SQLDAO implements DAO<Utente> {


    public UtenteDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Utente> doRetrieveAll(int start, int end) throws SQLException {

        final List<Utente> utenti = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM("Utenti").LIMIT(start, end).toString();

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

    @Override
    public Optional<Utente> doRetrieve(Utente key) throws SQLException {

        Utente utente = null;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM("Utenti").WHERE("Utenti.email = ?").toString();

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

            String query = QueryBuilder.INSERT_INTO("Utenti", new HashMap<>() {{

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

            String query = QueryBuilder.UPDATE("Utenti").SET(values).WHERE(condition).toString();

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

            String query = QueryBuilder.DELETE_FROM("Utenti").WHERE(condition).toString();

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows > 0;
    }


}
