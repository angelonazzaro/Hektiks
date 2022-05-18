package Model.Genere;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.GENERI;

public class GenereDAO extends SQLDAO implements DAO<Genere> {

    public GenereDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Genere> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(GENERI, condition, new GenereExtractor(), this.source);
    }

    @Override
    public List<Genere> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Genere obj) throws SQLException {

        return genericDoSave(GENERI, new HashMap<>() {{

            put("nome_genere", obj.getNome_genere());

        }}, this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(GENERI, condition, values, this.source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(condition, GENERI, this.source);
    }
}
