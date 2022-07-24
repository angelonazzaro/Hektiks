package Model.Pagamento;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.PAGAMENTI;

public class PagamentoExtractor implements ResultSetExtractor<Pagamento> {

    @Override
    public Pagamento extract(ResultSet resultSet, String... tables) throws SQLException {

        Pagamento pagamento = new Pagamento();

        pagamento.setEmail_utente(resultSet.getString(PAGAMENTI + ".email_utente"));
        pagamento.setCodice_ordine(resultSet.getString(PAGAMENTI + ".codice_ordine"));
        pagamento.setData_ora_pagamento(resultSet.getTimestamp(PAGAMENTI + ".data_ora_pagamento"));
        pagamento.setImporto(resultSet.getDouble(PAGAMENTI + ".importo"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */

        if (tables.length > 0) {
            pagamento.setJoin(new ArrayList<>());
            for (String table : tables)
                pagamento.addToJoin(findExtractor(table).extract(resultSet));
        }

        return pagamento;
    }
}
