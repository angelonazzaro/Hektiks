package Model.Pagamento;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.PAGAMENTI;

public class PagamentoExtractor implements ResultSetExtractor<Pagamento> {

    @Override
    public Pagamento extract(ResultSet resultSet) throws SQLException {

        Pagamento pagamento = new Pagamento();

        pagamento.setEmail_utente(resultSet.getString(PAGAMENTI + ".email_utente"));
        pagamento.setCodice_ordine(resultSet.getString(PAGAMENTI + ".codice_ordine"));
        pagamento.setData_ora_pagamento(resultSet.getTimestamp(PAGAMENTI + ".data_ora_pagamento"));
        pagamento.setImporto(resultSet.getDouble(PAGAMENTI + ".importo"));

        return pagamento;
    }
}
