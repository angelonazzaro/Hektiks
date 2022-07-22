package Model.Prodotto;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe che rappresenta il Bean del prodotto
 * fornisce metodi getter, setter, toString e toHashMap
 **/


public class Prodotto extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = -3011038005386033858L;

    private String email_utente;
    private String codice_gioco;
    private int quantita_disponibile;

    @Override
    public String toString() {
        return "Prodotto{" +
                "email_utente='" + email_utente + '\'' +
                ", codice_gioco='" + codice_gioco + '\'' +
                ", quantita_disponibile=" + quantita_disponibile +
                '}';
    }

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("email_utente", email_utente);
                put("codice_gioco", codice_gioco);
                put("quantita_disponibile", quantita_disponibile);
            }
        };
    }

    public String getEmail_utente() {
        return email_utente;
    }

    public void setEmail_utente(String email_utente) {
        this.email_utente = email_utente;
    }

    public String getCodice_gioco() {
        return codice_gioco;
    }

    public void setCodice_gioco(String codice_gioco) {
        this.codice_gioco = codice_gioco;
    }

    public int getQuantita_disponibile() {
        return quantita_disponibile;
    }

    public void setQuantita_disponibile(int quantita_disponibile) {
        this.quantita_disponibile = quantita_disponibile;
    }
}
