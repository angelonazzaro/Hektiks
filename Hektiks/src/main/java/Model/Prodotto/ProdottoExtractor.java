package Model.Prodotto;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.PRODOTTI;

public class ProdottoExtractor implements ResultSetExtractor<Prodotto> {

    @Override
    public Prodotto extract(ResultSet resultSet, String... tables) throws SQLException {

        Prodotto prodotto = new Prodotto();

        prodotto.setEmail_utente(resultSet.getString(PRODOTTI + ".email_utente"));
        prodotto.setCodice_gioco(resultSet.getString(PRODOTTI + ".codice_gioco"));
        prodotto.setQuantita_disponibile(resultSet.getInt(PRODOTTI + ".quantita_disponibile"));

        return prodotto;
    }
}
