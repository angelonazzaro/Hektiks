package Model.Prodotto_Ordine;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.PRODOTTI_ORDINI;

public class Prodotto_OrdineExtractor implements ResultSetExtractor<Prodotto_Ordine> {

    @Override
    public Prodotto_Ordine extract(ResultSet resultSet) throws SQLException {

        Prodotto_Ordine prodotto_ordine = new Prodotto_Ordine();

        prodotto_ordine.setEmail_utente(resultSet.getString(PRODOTTI_ORDINI + ".email_utente"));
        prodotto_ordine.setCodice_ordine(resultSet.getString(PRODOTTI_ORDINI + ".codice_ordine"));
        prodotto_ordine.setCodice_gioco(resultSet.getString(PRODOTTI_ORDINI + ".codice_gioco"));
        prodotto_ordine.setData_ora_creazione(resultSet.getTimestamp(PRODOTTI_ORDINI + ".data_ora_creazione"));
        prodotto_ordine.setQuantita(resultSet.getInt(PRODOTTI_ORDINI + ".quantita"));

        return prodotto_ordine;
    }
}
