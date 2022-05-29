package Model.Carrello;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

import static Model.Storage.Entities.CARRELLI;

public class CarrelloDAO extends SQLDAO implements DAO<Carrello> {

    public CarrelloDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Carrello> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(CARRELLI, condition, new CarrelloExtractor(), this.source);
    }

    @Override
    public <K> Carrello doRetrieveByKey(K key) throws SQLException {
        return null;
    }

    @Override
    public List<Carrello> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }


    @Override
    public boolean doSave(Carrello obj) throws SQLException {

        return genericDoSave(CARRELLI, new HashMap<>() {{
            put("email_utente", obj.getEmail_utente());
            put("data_creazione", obj.getData_creazione().toString());
            put("data_modifica", obj.getData_modifica().toString());

        }}, this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(CARRELLI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Carrello obj) throws SQLException {
        return false;
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(CARRELLI, condition, this.source);
    }
}
