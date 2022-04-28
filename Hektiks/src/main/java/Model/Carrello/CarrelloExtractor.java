package Model.Carrello;

import Model.Storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.CARRELLI;

public class CarrelloExtractor implements ResultSetExtractor<Carrello> {

    @Override
    public Carrello extract(ResultSet resultSet) throws SQLException {

        Carrello car = new Carrello();

        car.setEmail_utente(resultSet.getString(CARRELLI + ".email_utente"));
        car.setData_creazione(resultSet.getDate(CARRELLI + ".data_creazione"));
        car.setData_modifica(resultSet.getDate(CARRELLI + ".data_modifica"));

        return car;
    }
}
