package Model.Gioco;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;

/**
 * Classe che rappresenta il Bean del gioco
 * fornisce metodi getter, setter, toString e toHashMap
 **/

public class Gioco extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = 4089603497457100111L;

    private String codice_gioco;
    private String titolo;
    private String descrizione;
    private String trailer;
    private Date data_uscita;
    private String copertina;
    private double prezzo;
    private int quantita_disponibile;
    private int numero_vendite;
    private double percentuale_sconto;

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("codice_gioco", codice_gioco);
                put("titolo", titolo);
                put("descrizione", descrizione);
                put("trailer", trailer);
                put("data_uscita", data_uscita.toString());
                put("copertina", copertina);
                put("prezzo", prezzo);
                put("quantita_disponibile", quantita_disponibile);
                put("numero_vendite", numero_vendite);
                put("percentuale_sconto", percentuale_sconto);
            }
        };
    }

    @Override
    public String toString() {
        return "Gioco{" +
                "codice_gioco='" + codice_gioco + '\'' +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", trailer='" + trailer + '\'' +
                ", data_uscita=" + data_uscita +
                ", copertina='" + copertina + '\'' +
                ", prezzo=" + prezzo +
                ", quantita_disponibile=" + quantita_disponibile +
                ", numero_vendite=" + numero_vendite +
                ", percentuale_sconto=" + percentuale_sconto +
                '}';
    }

    public double getPercentuale_sconto() {
        return percentuale_sconto;
    }

    public void setPercentuale_sconto(double percentuale_sconto) {
        this.percentuale_sconto = percentuale_sconto;
    }

    public String getCodice_gioco() {
        return codice_gioco;
    }

    public void setCodice_gioco(String codice_gioco) {
        this.codice_gioco = codice_gioco;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Date getData_uscita() {
        return data_uscita;
    }

    public void setData_uscita(Date data_uscita) {
        this.data_uscita = data_uscita;
    }

    public String getCopertina() {
        return copertina;
    }

    public void setCopertina(String copertina) {
        this.copertina = copertina;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita_disponibile() {
        return quantita_disponibile;
    }

    public void setQuantita_disponibile(int quantita_disponibile) {
        this.quantita_disponibile = quantita_disponibile;
    }

    public int getNumero_vendite() {
        return numero_vendite;
    }

    public void setNumero_vendite(int numero_vendite) {
        this.numero_vendite = numero_vendite;
    }

}
