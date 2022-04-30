package Model.Sconto;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.SCONTI;

public class ScontoExtractor implements ResultSetExtractor<Sconto> {
    @Override
    public Sconto extract(ResultSet resultSet) throws SQLException {

        Sconto sconto = new Sconto();

        sconto.setCodice_gioco(resultSet.getString(SCONTI + ".codice_gioco"));
        sconto.setCodice_sconto(resultSet.getString(SCONTI + ".codice_sconto"));
        sconto.setData_creazione(resultSet.getDate(SCONTI + ".data_creazione"));
        sconto.setPrecentuale(resultSet.getByte(SCONTI + ".precentuale"));
        sconto.setData_fine(resultSet.getDate(SCONTI + ".data_fine"));

        return sconto;
    }
}
