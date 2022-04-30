package Model.Sconto;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.SCONTI;

public class ScontoExtractor implements ResultSetExtractor<Sconto> {
    @Override
    public Sconto extract(ResultSet resultSet) throws SQLException {

        Sconto sco = new Sconto();

        sco.setCodice_gioco(resultSet.getString(SCONTI + ".codice_gioco"));
        sco.setCodice_sconto(resultSet.getString(SCONTI + ".codice_sconto"));
        sco.setData_creazione(resultSet.getDate(SCONTI + ".data_creazione"));
        sco.setPrecentuale(resultSet.getByte(SCONTI + ".precentuale"));
        sco.setData_fine(resultSet.getDate(SCONTI + ".data_fine"));

        return sco;
    }
}
