package Model.Prodotto;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
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
    public List<Prodotto> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Prodotto obj) throws SQLException {

        return genericDoSave(PRODOTTI, new HashMap<>() {{
                    put("email_utente", obj.getEmail_utente());
                    put("codice_gioco", obj.getCodice_gioco());
                    put("quantita_disponibile", obj.getQuantita_disponibile());
                }},
                this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(PRODOTTI, condition, values, this.source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(PRODOTTI, condition, this.source);
    }
}
