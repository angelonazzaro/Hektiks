package Model.Recensione;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

public class Recensione extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = -6606424426975407660L;

    private String email_utente;
    private String codice_gioco;
    private Timestamp data_ora_pubblicazione;
    private byte percentuale;
    private String descrizione;

    @Override
    public String toString() {
        return "Recensione{" +
                "email_utente='" + email_utente + '\'' +
                ", codice_gioco='" + codice_gioco + '\'' +
                ", data_ora_pubblicazione=" + data_ora_pubblicazione +
                ", percentuale=" + percentuale +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("email_utente", email_utente);
                put("codice_gioco", codice_gioco);
                put("data_ora_pubblicazione", data_ora_pubblicazione.toString());
                put("percentuale", percentuale);
                put("descrizione", descrizione);
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

    public Timestamp getData_ora_pubblicazione() {
        return data_ora_pubblicazione;
    }

    public void setData_ora_pubblicazione(Timestamp data_ora_pubblicazione) {
        this.data_ora_pubblicazione = data_ora_pubblicazione;
    }

    public byte getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(byte percentuale) {
        this.percentuale = percentuale;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
