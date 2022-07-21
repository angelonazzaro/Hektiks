package Model.Prodotto;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.PRODOTTI;

public class ProdottoDAO extends SQLDAO implements DAO<Prodotto> {

    public ProdottoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Prodotto> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(PRODOTTI, condition, new ProdottoExtractor(), this.source);
    }

    @Override
    public List<Prodotto> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(PRODOTTI, joinType, joinCondition, condition, new ProdottoExtractor(), this.source, tables);
    }

    @Override
    public Prodotto doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 2)
            throw new InvalidPrimaryKeyException();

        List<Prodotto> prodotto = doRetrieveByCondition(
                String.format("%s.email_utente = '%s' AND %s.codice_gioco = '%s'",
                        PRODOTTI, key[0], PRODOTTI, key[1]));
        return prodotto.isEmpty() ? null : prodotto.get(0);
    }

    @Override
    public List<Prodotto> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<Prodotto> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<Prodotto> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(Prodotto obj) throws SQLException {

        return genericDoSave(PRODOTTI, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(PRODOTTI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Prodotto obj) throws SQLException {

        if (doRetrieveByKey(obj.getEmail_utente(), obj.getCodice_gioco()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.email_utente = '%s' AND %s.codice_gioco = '%s'",
                        PRODOTTI, obj.getEmail_utente(), PRODOTTI, obj.getCodice_gioco()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(PRODOTTI, condition, this.source);
    }
}
