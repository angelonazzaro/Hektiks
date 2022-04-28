package Model.Carrello;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Carrello implements Serializable {

    @Serial
    private static final long serialVersionUID = -5843497590632721035L;

    private String email_utente;
    private Date data_creazione;
    private Date data_modifica;

    public Carrello(){

    }

    @Override
    public String toString() {
        return "Carrello{" +
                "email_utente='" + email_utente + '\'' +
                ", data_creazione=" + data_creazione +
                ", data_modifica=" + data_modifica +
                '}';
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
