package Model.Prodotto;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.PRODOTTI;

public class ProdottoExtractor implements ResultSetExtractor<Prodotto> {

    @Override
    public Prodotto extract(ResultSet resultSet) throws SQLException {

        Prodotto pro = new Prodotto();

        pro.setEmail_utente(resultSet.getString(PRODOTTI + ".email_utente"));
        pro.setCodice_gioco(resultSet.getString(PRODOTTI + ".codice_gioco"));
        pro.setQuantita_disponibile(resultSet.getInt(PRODOTTI + ".quantita_disponibile"));

        return pro;
    }
}
