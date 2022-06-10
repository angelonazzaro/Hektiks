package Model.Ordine;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.ORDINI;

public class OrdineExtractor implements ResultSetExtractor<Ordine> {

    @Override
    public Ordine extract(ResultSet resultSet, String... tables) throws SQLException {

        Ordine ordine = new Ordine();

        ordine.setEmail_utente(resultSet.getString(ORDINI + ".email_utente"));
        ordine.setCodice_ordine(resultSet.getString(ORDINI + ".codice_ordine"));
        ordine.setData_ora_ordinazione(resultSet.getTimestamp(ORDINI + ".data_ora_ordinazione"));
        ordine.setPrezzo_totale(resultSet.getDouble(ORDINI + ".prezzo_totale"));

        return ordine;
    }
}
