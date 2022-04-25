package Model.Utente;

import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SQLUtenteDAO extends SQLDAO implements UtenteDAO<SQLException> {


    public SQLUtenteDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Utente> fetchUtenti(int start, int end) throws SQLException {
        return null;
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
