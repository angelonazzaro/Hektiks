package Model.Prodotto_Ordine;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
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
    public Prodotto_Ordine doRetrieveByKey(Object... key) throws SQLException {
        return null;
    }

    @Override
    public List<Prodotto_Ordine> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Prodotto_Ordine obj) throws SQLException {

        return genericDoSave(PRODOTTI_ORDINI, new HashMap<>() {{
                    put("email_utente", obj.getEmail_utente());
                    put("codice_ordine", obj.getCodice_ordine());
                    put("codice_gioco", obj.getCodice_gioco());
                    put("data_ora_creazione", obj.getData_ora_creazione().toString());
                    put("quantità", obj.getQuantita());
                }},
                this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(PRODOTTI_ORDINI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Prodotto_Ordine obj) throws SQLException {
        return false;
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(PRODOTTI_ORDINI, condition, this.source);

    }
}
