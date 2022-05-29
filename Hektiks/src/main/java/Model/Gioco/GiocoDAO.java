package Model.Gioco;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.GIOCHI;

public class GiocoDAO extends SQLDAO implements DAO<Gioco> {

    public GiocoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Gioco> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(GIOCHI, condition, new GiocoExtracotor(), this.source);
    }

    @Override
    public <K> Gioco doRetrieveByKey(K key) throws SQLException {
        return null;
    }

    @Override
    public List<Gioco> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Gioco obj) throws SQLException {

        return genericDoSave(GIOCHI, new HashMap<>() {{
                    put("codice_gioco", obj.getCodice_gioco());
                    put("titolo", obj.getTitolo());
                    put("descrizione", obj.getDescrizione());
                    put("trailer", obj.getTrailer());
                    put("data_uscita", obj.getData_uscita().toString());
                    put("copertina", obj.getCopertina());
                    put("prezzo", obj.getPrezzo());
                    put("quantita_disponibile", obj.getQuantita_disponibile());
                    put("numero_vendite", obj.getNumero_vendite());
                }},
                this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(GIOCHI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Gioco obj) throws SQLException {
        return false;
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(GIOCHI, condition, this.source);
    }
}
