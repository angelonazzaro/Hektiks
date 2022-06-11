package Model.Sconto;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.SCONTI;

public class ScontoExtractor implements ResultSetExtractor<Sconto> {
    @Override
    public Sconto extract(ResultSet resultSet, String... tables) throws SQLException {

        Sconto sconto = new Sconto();

        sconto.setCodice_gioco(resultSet.getString(SCONTI + ".codice_gioco"));
        sconto.setCodice_sconto(resultSet.getString(SCONTI + ".codice_sconto"));
        sconto.setData_creazione(resultSet.getDate(SCONTI + ".data_creazione"));
        sconto.setPercentuale(resultSet.getByte(SCONTI + ".percentuale"));
        sconto.setData_fine(resultSet.getDate(SCONTI + ".data_fine"));

        if (tables.length > 0) {
            sconto.setJoin(new ArrayList<>());
            for (String table : tables) sconto.addToJoin(findExtractor(table).extract(resultSet));
        }

        return sconto;
    }
}
