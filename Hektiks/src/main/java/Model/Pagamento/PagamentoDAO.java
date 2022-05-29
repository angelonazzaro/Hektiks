package Model.Pagamento;

import Model.Storage.DAO;
import Model.Storage.SQLDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.Storage.Entities.PAGAMENTI;

public class PagamentoDAO extends SQLDAO implements DAO<Pagamento> {

    public PagamentoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Pagamento> doRetrieveByCondition(String condition) throws SQLException {

        return genericDoRetrieveByCondition(PAGAMENTI, condition, new PagamentoExtractor(), this.source);
    }

    @Override
    public Pagamento doRetrieveByKey(Object... key) throws SQLException {

        List<Pagamento> pagamento = doRetrieveByCondition(PAGAMENTI + ".email_utente = " + "'" + key.toString() + "'");
        return pagamento.isEmpty() ? null : pagamento.get(0);
    }

    @Override
    public List<Pagamento> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public boolean doSave(Pagamento obj) throws SQLException {

        return genericDoSave(PAGAMENTI, new HashMap<>() {{
                    put("email_utente", obj.getEmail_utente());
                    put("codice_ordine", obj.getCodice_ordine());
                    put("data_ora_pagamento", obj.getData_ora_pagamento().toString());
                    put("importo", obj.getImporto());

                }},
                this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(PAGAMENTI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Pagamento obj) throws SQLException {
        return false;
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(PAGAMENTI, condition, this.source);
    }
}
