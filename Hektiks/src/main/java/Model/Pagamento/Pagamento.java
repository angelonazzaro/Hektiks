package Model.Pagamento;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Classe che rappresenta il Bean del pagamento
 * fornisce metodi getter, setter, toString e toHashMap
 **/

public class Pagamento extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = -8787575923741811571L;

    private String email_utente;
    private String codice_ordine;
    private Timestamp data_ora_pagamento;
    private double importo;

    @Override
    public String toString() {
        return "Pagamento{" +
                "email_utente='" + email_utente + '\'' +
                ", codice_ordine='" + codice_ordine + '\'' +
                ", data_ora_pagamento=" + data_ora_pagamento +
                ", importo=" + importo +
                '}';
    }

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("email_utente", email_utente);
                put("codice_ordine", codice_ordine);
                put("data_ora_pagamento", data_ora_pagamento.toString());
                put("importo", importo);
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

    public Timestamp getData_ora_pagamento() {
        return data_ora_pagamento;
    }

    public void setData_ora_pagamento(Timestamp data_ora_pagamento) {
        this.data_ora_pagamento = data_ora_pagamento;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

}
