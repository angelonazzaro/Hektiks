package Model.Carrello;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Storage.Entities.CARRELLI;

public class CarrelloExtractor implements ResultSetExtractor<Carrello> {

    @Override
    public Carrello extract(ResultSet resultSet, String... tables) throws SQLException {

        Carrello carrello = new Carrello();

        carrello.setEmail_utente(resultSet.getString(CARRELLI + ".email_utente"));
        carrello.setData_creazione(resultSet.getDate(CARRELLI + ".data_creazione"));
        carrello.setData_modifica(resultSet.getDate(CARRELLI + ".data_modifica"));

        /*
         * Se ci sono altre tabelle (oltre a quella "implicita")
         * si aggiunge alla lista di oggetti della join i field estratti tremite l'Extractor
         */
        if (tables.length > 0) {
            carrello.setJoin(new ArrayList<>());
            for (String table : tables)
                carrello.addToJoin(findExtractor(table).extract(resultSet));
        }

        return carrello;
    }
}
