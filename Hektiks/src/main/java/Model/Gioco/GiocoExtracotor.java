package Model.Gioco;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.GIOCHI;

public class GiocoExtracotor implements ResultSetExtractor<Gioco> {

    @Override
    public Gioco extract(ResultSet resultSet) throws SQLException {

        Gioco gio = new Gioco();

        gio.setCodice_gioco(resultSet.getString(GIOCHI + ".codice_gioco"));
        gio.setTitolo(resultSet.getString(GIOCHI + ".titolo"));
        gio.setDescrizione(resultSet.getString(GIOCHI + ".descrizione"));
        gio.setTrailer(resultSet.getString(GIOCHI + ".trailer"));
        gio.setData_uscita(resultSet.getDate(GIOCHI + ".data_uscita"));
        gio.setCopertina(resultSet.getString(GIOCHI + ".copertina"));
        gio.setPrezzo(resultSet.getDouble(GIOCHI + ".prezzo"));
        gio.setQuantita_disponibile(resultSet.getInt(GIOCHI + ".quantita_disponibile"));
        gio.setNumero_vendite(resultSet.getInt(GIOCHI + ".numero_vendite"));

        return gio;
    }
}
