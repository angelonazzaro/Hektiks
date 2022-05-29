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
    public Genere doRetrieveByKey(Object... key) throws SQLException {

        List<Genere> genere = doRetrieveByCondition(GENERI + ".nome_genere = " + "'" + key.toString() + "'");
        return genere.isEmpty() ? null : genere.get(0);
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
    public boolean doSaveOrUpdate(Genere obj) throws SQLException {

        if (doRetrieveByKey(obj.getNome_genere()) == null)
            return doSave(obj);

        return doUpdate(new HashMap<>() {{
            put("nome_genere", obj.getNome_genere());

        }}, GENERI + ".nome_genere = " + "'" + obj.getNome_genere() + "'");
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(GENERI, condition, this.source);
    }
}
