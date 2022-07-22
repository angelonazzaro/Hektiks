package Model.Carrello;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;

/**
 * Classe che rappresenta il Bean del carrello
 * fornisce metodi getter, setter, toString e toHashMap
 **/

public class Carrello extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = -5843497590632721035L;

    private String email_utente;
    private Date data_creazione;
    private Date data_modifica;

    @Override
    public String toString() {
        return "Carrello{" +
                "email_utente='" + email_utente + '\'' +
                ", data_creazione=" + data_creazione +
                ", data_modifica=" + data_modifica +
                '}';
    }

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("email_utente", email_utente);
                put("data_creazione", data_creazione.toString());
                put("data_modifica", data_modifica.toString());
            }
        };
    }

    public String getEmail_utente() {
        return email_utente;
    }

    public void setEmail_utente(String email_utente) {
        this.email_utente = email_utente;
    }

    public Date getData_creazione() {
        return data_creazione;
    }

    public void setData_creazione(Date data_creazione) {
        this.data_creazione = data_creazione;
    }

    public Date getData_modifica() {
        return data_modifica;
    }

    public void setData_modifica(Date data_modifica) {
        this.data_modifica = data_modifica;
    }
}
