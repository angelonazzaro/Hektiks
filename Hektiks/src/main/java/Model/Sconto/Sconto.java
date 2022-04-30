package Model.Sconto;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

public class Sconto implements Serializable  {

    @Serial
    private static final long serialVersionUID = -6140573396807378229L;

    private String codice_sconto;
    private String codice_gioco;
    private Date data_creazione;
    private byte precentuale;
    private Date data_fine;

    public Sconto() {

    }

    @Override
    public String toString() {
        return "Sconto{" +
                "codice_sconto='" + codice_sconto + '\'' +
                ", codice_gioco='" + codice_gioco + '\'' +
                ", data_creazione=" + data_creazione +
                ", precentuale=" + precentuale +
                ", data_fine=" + data_fine +
                '}';
    }

    public String getCodice_sconto() {
        return codice_sconto;
    }

    public void setCodice_sconto(String codice_sconto) {
        this.codice_sconto = codice_sconto;
    }

    public String getCodice_gioco() {
        return codice_gioco;
    }

    public void setCodice_gioco(String codice_gioco) {
        this.codice_gioco = codice_gioco;
    }

    public Date getData_creazione() {
        return data_creazione;
    }

    public void setData_creazione(Date data_creazione) {
        this.data_creazione = data_creazione;
    }

    public byte getPrecentuale() {
        return precentuale;
    }

    public void setPrecentuale(byte precentuale) {
        this.precentuale = precentuale;
    }

    public Date getData_fine() {
        return data_fine;
    }

    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
    }

}
