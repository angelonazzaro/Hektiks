package Model.Utente;

import Model.Storage.QueryBuilder;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SQLUtenteDAO extends SQLDAO implements UtenteDAO<SQLException> {


    public SQLUtenteDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Utente> fetchUtenti(int start, int end) throws SQLException {

        List<Utente> utenti = new ArrayList<>();

        try(Connection conn = source.getConnection()){

            String query = QueryBuilder.SELECT("*").FROM("Utenti").LIMIT(start, end).toString();

            try(PreparedStatement ps = conn.prepareStatement(query)){
                ResultSet set = ps.executeQuery();
                UtenteExtractor utenteExtractor = new UtenteExtractor();

                while (set.next()){

                    utenti.add(utenteExtractor.extract(set));
                }
            }
        }
        return utenti;
    }

    @Override
    public Optional<Utente> fetchUtente(String email) throws SQLException {

        Utente utente = null;
        try(Connection conn = source.getConnection()){

            String query = QueryBuilder.SELECT("*").FROM("Utenti").WHERE("Utenti.email = ?").toString();

            try(PreparedStatement ps = conn.prepareStatement(query)){

                ps.setString(1, email);
                ResultSet set = ps.executeQuery();

                if (set.next()){

                    utente = new UtenteExtractor().extract(set);
                }
            }
        }

        return Optional.ofNullable(utente);
    }

    @Override
    public boolean createUtente(Utente utente) throws SQLException {

        int rows = 0;
        try(Connection conn = source.getConnection()){

            String query = QueryBuilder.INSERT_INTO("Utenti", new HashMap<>(){{

                put("email", utente.getEmail());
                put("username", utente.getUsername());
                put("password", utente.getPassword_utente());
                put("data_registrazione", utente.getData_registrazione());
                put("ruolo", utente.isRuolo());
                put("saldo", utente.getSaldo());
                put("biografia", utente.getBiografia());
            }}).toString();

            try (PreparedStatement ps = conn.prepareStatement(query)){

                rows = ps.executeUpdate();
            }
        }
        return rows == 1;
    }

    @Override
    public boolean updateUtente(Utente utente) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteUtente(String email) throws SQLException {
        return false;
    }
}
