package Model.GiftCard;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Classe che rappresenta il Bean delle giftcard
 * fornisce metodi getter, setter, toString e toHashMap
 **/

public class GiftCard extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = -733297996749609206L;

    private String codice_giftCard;
    private String email_utente;
    private double importo;
    private Timestamp data_ora_creazione;
    private Timestamp data_ora_utilizzo;

    @Override
    public String toString() {
        return "GiftCard{" +
                "codice_giftCard='" + codice_giftCard + '\'' +
                ", email_utente='" + email_utente + '\'' +
                ", importo=" + importo +
                ", data_ora_creazione=" + data_ora_creazione +
                ", data_utilizzo=" + data_ora_utilizzo +
                '}';
    }

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("codice_giftCard", codice_giftCard);
                if (email_utente != null)
                    put("email_utente", email_utente);

                put("importo", importo);

                put("data_ora_creazione", data_ora_creazione.toString());
                if (data_ora_utilizzo != null)
                    put("data_ora_utilizzo", data_ora_utilizzo.toString());
            }
        };
    }

    public String getCodice_giftCard() {
        return codice_giftCard;
    }

    public void setCodice_giftCard(String codice_giftCard) {
        this.codice_giftCard = codice_giftCard;
    }

    public String getEmail_utente() {
        return email_utente;
    }

    public void setEmail_utente(String email_utente) {
        this.email_utente = email_utente;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public Timestamp getData_ora_creazione() {
        return data_ora_creazione;
    }

    public void setData_ora_creazione(Timestamp data_ora_creazione) {
        this.data_ora_creazione = data_ora_creazione;
    }

    public Timestamp getData_ora_utilizzo() {
        return data_ora_utilizzo;
    }

    public void setData_ora_utilizzo(Timestamp data_utilizzo) {
        this.data_ora_utilizzo = data_utilizzo;
    }

}
