package Model.Utente;

import Model.Storage.QueryBuilder;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

            String query = QueryBuilder.SELECT("*").FROM("Utenti").toString();
            try(PreparedStatement ps = conn.prepareStatement(query)){

                ps.setInt(1, start);
                ps.setInt(2, end);
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
        return Optional.empty();
    }

    @Override
    public boolean createUtente(Utente utente) throws SQLException {
        return false;
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
