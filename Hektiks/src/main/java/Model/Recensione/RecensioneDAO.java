package Model.Recensione;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.RECENSIONI;

public class RecensioneDAO extends SQLDAO implements DAO<Recensione> {

    public RecensioneDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Recensione> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(RECENSIONI, condition, new RecensioneExtractor(), this.source);
    }

    @Override
    public List<Recensione> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Recensione obj) throws SQLException {

        return genericDoSave(RECENSIONI, new HashMap<>() {{
                    put("email_utente", obj.getEmail_utente());
                    put("codice_gioco", obj.getCodice_gioco());
                    put("data_ora_pubblicazione", obj.getData_ora_pubblicazione().toString());
                    put("percentuale", obj.getPercentuale());
                    put("descrizione", obj.getDescrizione());
                }},
                this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(RECENSIONI, condition, values, this.source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(RECENSIONI, condition, this.source);
    }
}
