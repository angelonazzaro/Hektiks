package Model.Genere;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
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
    public List<Genere> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(GENERI, joinType, joinCondition, condition, new GenereExtractor(), this.source);
    }

    @Override
    public Genere doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 1)
            throw new InvalidPrimaryKeyException();

        List<Genere> genere = doRetrieveByCondition(
                String.format("%s.nome_genere = '%s'", GENERI, key[0]));
        return genere.isEmpty() ? null : genere.get(0);
    }

    @Override
    public List<Genere> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<Genere> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<Genere> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(Genere obj) throws SQLException {

        return genericDoSave(GENERI, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(GENERI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Genere obj) throws SQLException {

        if (doRetrieveByKey(obj.getNome_genere()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.nome_genere = '%s'", GENERI, obj.getNome_genere()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(GENERI, condition, this.source);
    }
}
