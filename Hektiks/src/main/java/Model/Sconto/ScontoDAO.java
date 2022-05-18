package Model.Sconto;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.SCONTI;

public class ScontoDAO extends SQLDAO implements DAO<Sconto> {

    public ScontoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Sconto> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(SCONTI, condition, new ScontoExtractor(), this.source);
    }

    @Override
    public List<Sconto> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Sconto obj) throws SQLException {

        return genericDoSave(SCONTI, new HashMap<>() {{
                    put("codice_gioco", obj.getCodice_gioco());
                    put("codice_sconto", obj.getCodice_sconto());
                    put("data_creazione", obj.getData_creazione());
                    put("precentuale", obj.getPrecentuale());
                    put("data_fine", obj.getData_fine().toString());
                }},
                this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(SCONTI, condition, values, this.source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(SCONTI, condition, this.source);
    }
}
