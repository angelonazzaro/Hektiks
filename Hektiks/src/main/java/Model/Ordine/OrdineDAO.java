package Model.Ordine;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.ORDINI;

public class OrdineDAO extends SQLDAO implements DAO<Ordine> {

    public OrdineDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Ordine> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(ORDINI, condition, new OrdineExtractor(), this.source);
    }

    @Override
    public Ordine doRetrieveByKey(Object... key) throws SQLException {

        List<Ordine> ordine = doRetrieveByCondition(ORDINI + ".email_utente = " + "'" + key.toString() + "'");
        return ordine.isEmpty() ? null : ordine.get(0);
    }

    @Override
    public List<Ordine> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Ordine obj) throws SQLException {

        return genericDoSave(ORDINI, new HashMap<>() {{
                    put("email_utente", obj.getEmail_utente());
                    put("codice_ordine", obj.getCodice_ordine());
                    put("data_ora_ordinazione", obj.getData_ora_ordinazione().toString());
                    put("prezzo_totale", obj.getPrezzo_totale());
                }},
                this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(ORDINI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Ordine obj) throws SQLException {

        if (doRetrieveByKey(obj.getEmail_utente()) == null)
            return doSave(obj);

        return doUpdate(new HashMap<>() {{
            put("email_utente", obj.getEmail_utente());
            put("codice_ordine", obj.getCodice_ordine());
            put("data_ora_ordinazione", obj.getData_ora_ordinazione().toString());
            put("prezzo_totale", obj.getPrezzo_totale());

        }}, ORDINI + ".email_utente = " + "'" + obj.getEmail_utente() + "'");
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(ORDINI, condition, this.source);
    }
}
