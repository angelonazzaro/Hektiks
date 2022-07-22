package Model.Ordine;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Classe che rappresenta il Bean dell'ordine
 * fornisce metodi getter, setter, toString e toHashMap
 **/

public class Ordine extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = -6625776650308554938L;

    private String email_utente;
    private String codice_ordine;
    private Timestamp data_ora_ordinazione;
    private double prezzo_totale;

    @Override
    public String toString() {
        return "Ordine{" +
                "email_utente='" + email_utente + '\'' +
                ", codice_ordine='" + codice_ordine + '\'' +
                ", data_ora_ordinazione=" + data_ora_ordinazione +
                ", prezzo_totale=" + prezzo_totale +
                '}';
    }

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("email_utente", email_utente);
                put("codice_ordine", codice_ordine);
                put("data_ora_ordinazione", data_ora_ordinazione.toString());
                put("prezzo_totale", prezzo_totale);
            }
        };
    }

    public String getEmail_utente() {
        return email_utente;
    }

    public void setEmail_utente(String email_utente) {
        this.email_utente = email_utente;
    }

    public String getCodice_ordine() {
        return codice_ordine;
    }

    public void setCodice_ordine(String codice_ordine) {
        this.codice_ordine = codice_ordine;
    }

    public Timestamp getData_ora_ordinazione() {
        return data_ora_ordinazione;
    }

    public void setData_ora_ordinazione(Timestamp data_ora_ordinazione) {
        this.data_ora_ordinazione = data_ora_ordinazione;
    }

    public double getPrezzo_totale() {
        return prezzo_totale;
    }

    public void setPrezzo_totale(double prezzo_totale) {
        this.prezzo_totale = prezzo_totale;
    }

}
