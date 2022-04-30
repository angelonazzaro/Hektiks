package Model.Recensione;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.RECENSIONI;

public class RecensioneExtractor implements ResultSetExtractor<Recensione> {

    @Override
    public Recensione extract(ResultSet resultSet) throws SQLException {

        Recensione recensione = new Recensione();

        recensione.setEmail_utente(resultSet.getString(RECENSIONI + ".email_utente"));
        recensione.setCodice_gioco(resultSet.getString(RECENSIONI + ".codice_gioco"));
        recensione.setData_ora_pubblicazione(resultSet.getTimestamp(RECENSIONI + ".data_ora_pubblicazione"));
        recensione.setPercentuale(resultSet.getByte(RECENSIONI + ".percentuale"));
        recensione.setDescrizione(resultSet.getString(RECENSIONI + ".descrizione"));

        return recensione;
    }
}
