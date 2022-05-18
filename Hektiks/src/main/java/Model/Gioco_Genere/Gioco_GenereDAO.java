package Model.Gioco_Genere;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Gioco_GenereDAO extends SQLDAO implements DAO<Gioco_Genere> {

    public Gioco_GenereDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Gioco_Genere> doRetrieveByCondition(String condition) throws SQLException {
        return null;
    }

    @Override
    public List<Gioco_Genere> doRetrieveAll() throws SQLException {
        return null;
    }

    @Override
    public boolean doSave(Gioco_Genere obj) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {
        return false;
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return false;
    }
}
