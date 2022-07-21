package Model.Gioco_Genere;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.GIOCHI_GENERE;

public class Gioco_GenereDAO extends SQLDAO implements DAO<Gioco_Genere> {

    public Gioco_GenereDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Gioco_Genere> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(GIOCHI_GENERE, condition, new Gioco_GenereExtractor(), this.source);
    }

    @Override
    public List<Gioco_Genere> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(GIOCHI_GENERE, joinType, joinCondition, condition, new Gioco_GenereExtractor(), this.source, tables);
    }

    @Override
    public Gioco_Genere doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 2)
            throw new InvalidPrimaryKeyException();

        List<Gioco_Genere> gioco_Genere = doRetrieveByCondition(
                String.format("%s.codice_gioco = '%s' AND %s.nome_genere = '%s'", GIOCHI_GENERE, key[0], GIOCHI_GENERE,
                        key[1]));
        return gioco_Genere.isEmpty() ? null : gioco_Genere.get(0);
    }

    @Override
    public List<Gioco_Genere> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<Gioco_Genere> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<Gioco_Genere> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(Gioco_Genere obj) throws SQLException {

        return genericDoSave(GIOCHI_GENERE, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(GIOCHI_GENERE, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Gioco_Genere obj) throws SQLException {

        if (doRetrieveByKey(obj.getCodice_gioco(), obj.getNome_genere()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.codice_gioco = '%s' AND %s.nome_genere = '%s'",
                        GIOCHI_GENERE, obj.getCodice_gioco(), GIOCHI_GENERE, obj.getNome_genere()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(GIOCHI_GENERE, condition, this.source);
    }
}
