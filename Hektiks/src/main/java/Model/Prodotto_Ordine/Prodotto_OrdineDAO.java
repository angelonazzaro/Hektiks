package Model.Prodotto_Ordine;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.PRODOTTI_ORDINI;

public class Prodotto_OrdineDAO extends SQLDAO implements DAO<Prodotto_Ordine> {

    public Prodotto_OrdineDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Prodotto_Ordine> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(PRODOTTI_ORDINI, condition, new Prodotto_OrdineExtractor(), this.source);
    }

    @Override
    public List<Prodotto_Ordine> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(PRODOTTI_ORDINI, joinType, joinCondition, condition, new Prodotto_OrdineExtractor(), this.source, tables);
    }

    @Override
    public Prodotto_Ordine doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 4)
            throw new InvalidPrimaryKeyException();

        List<Prodotto_Ordine> prodotto_Ordine = doRetrieveByCondition(
                String.format("%s.email_utente = '%s' AND %s.codice_ordine = '%s' AND %s.codice_gioco = '%s' AND %s.data_ora_creazione = '%s'",
                        PRODOTTI_ORDINI, key[0], PRODOTTI_ORDINI, key[1], PRODOTTI_ORDINI, key[2], PRODOTTI_ORDINI, key[3]));
        return prodotto_Ordine.isEmpty() ? null : prodotto_Ordine.get(0);
    }

    @Override
    public List<Prodotto_Ordine> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<Prodotto_Ordine> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<Prodotto_Ordine> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(Prodotto_Ordine obj) throws SQLException {

        return genericDoSave(PRODOTTI_ORDINI, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(PRODOTTI_ORDINI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Prodotto_Ordine obj) throws SQLException {

        if (doRetrieveByKey(obj.getEmail_utente(), obj.getCodice_ordine(), obj.getCodice_gioco(), obj.getData_ora_creazione().toString()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.email_utente = '%s' AND %s.codice_ordine = '%s' AND %s.codice_gioco = '%s' AND %s.data_ora_creazione = '%s'",
                        PRODOTTI_ORDINI, obj.getCodice_ordine(), PRODOTTI_ORDINI, obj.getCodice_ordine(), PRODOTTI_ORDINI, obj.getCodice_gioco(), PRODOTTI_ORDINI, obj.getData_ora_creazione().toString()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(PRODOTTI_ORDINI, condition, this.source);

    }
}
