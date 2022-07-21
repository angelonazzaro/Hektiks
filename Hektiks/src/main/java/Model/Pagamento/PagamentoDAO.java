package Model.Pagamento;


import Model.Storage.DAO;
import Model.Storage.SQLDAO;
import Utils.InvalidPrimaryKeyException;

import javax.sql.DataSource;
import java.sql.SQLException;
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
    public List<Pagamento> doRetrieveByJoin(String joinType, String joinCondition, String condition, String... tables) throws SQLException {

        return genericDoRetrieveByJoin(PAGAMENTI, joinType, joinCondition, condition, new PagamentoExtractor(), this.source);
    }

    @Override
    public Pagamento doRetrieveByKey(String... key) throws SQLException {

        if (key == null || key.length != 3)
            throw new InvalidPrimaryKeyException();

        List<Pagamento> pagamento = doRetrieveByCondition(
                String.format("%s.email_utente = '%s' AND %s.codice_ordine = '%s' AND %s.data_ora_pagamento = '%s'",
                        PAGAMENTI, key[0], PAGAMENTI, key[1], PAGAMENTI, key[2]));
        return pagamento.isEmpty() ? null : pagamento.get(0);
    }

    @Override
    public List<Pagamento> doRetrieveAll() throws SQLException {

        return doRetrieveByCondition("TRUE");
    }

    @Override
    public List<Pagamento> doRetrieveAll(int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + row_count);
    }

    @Override
    public List<Pagamento> doRetrieveAll(int offset, int row_count) throws SQLException {

        return doRetrieveByCondition("TRUE LIMIT " + offset + ", " + row_count);
    }

    @Override
    public boolean doSave(Pagamento obj) throws SQLException {

        return genericDoSave(PAGAMENTI, obj.toHashMap(), this.source);
    }

    @Override
    public boolean doUpdate(Map<String, ?> values, String condition) throws SQLException {

        return genericDoUpdate(PAGAMENTI, condition, values, this.source);
    }

    @Override
    public boolean doSaveOrUpdate(Pagamento obj) throws SQLException {

        if (doRetrieveByKey(obj.getEmail_utente(), obj.getCodice_ordine(), obj.getData_ora_pagamento().toString()) == null)
            return doSave(obj);

        return doUpdate(obj.toHashMap(),
                String.format("%s.email_utente = '%s' AND %s.codice_ordine = '%s' AND %s.data_ora_pagamento = '%s'",
                        PAGAMENTI, obj.getEmail_utente(), PAGAMENTI, obj.getCodice_ordine(), PAGAMENTI, obj.getData_ora_pagamento().toString()));
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {

        return genericDoDelete(PAGAMENTI, condition, this.source);
    }
}
