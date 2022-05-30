package Model.Gioco;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.GIOCHI;

public class GiocoExtractor implements ResultSetExtractor<Gioco> {

    @Override
    public Gioco extract(ResultSet resultSet) throws SQLException {

        Gioco gioco = new Gioco();

        gioco.setCodice_gioco(resultSet.getString(GIOCHI + ".codice_gioco"));
        gioco.setTitolo(resultSet.getString(GIOCHI + ".titolo"));
        gioco.setDescrizione(resultSet.getString(GIOCHI + ".descrizione"));
        gioco.setTrailer(resultSet.getString(GIOCHI + ".trailer"));
        gioco.setData_uscita(resultSet.getDate(GIOCHI + ".data_uscita"));
        gioco.setCopertina(resultSet.getString(GIOCHI + ".copertina"));
        gioco.setPrezzo(resultSet.getDouble(GIOCHI + ".prezzo"));
        gioco.setQuantita_disponibile(resultSet.getInt(GIOCHI + ".quantita_disponibile"));
        gioco.setNumero_vendite(resultSet.getInt(GIOCHI + ".numero_vendite"));

        return gioco;
    }
}
