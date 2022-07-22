package Model.Gioco_Genere;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe che rappresenta il Bean dei giochi_genere
 * fornisce metodi getter, setter, toString e toHashMap
 **/

public class Gioco_Genere extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = -1076342045661803008L;

    private String codice_gioco;
    private String nome_genere;

    @Override
    public String toString() {
        return "Gioco_Genere{" +
                "codice_gioco='" + codice_gioco + '\'' +
                ", nome_genere='" + nome_genere + '\'' +
                '}';
    }

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("codice_gioco", codice_gioco);
                put("nome_genere", nome_genere);
            }
        };
    }

    public String getCodice_gioco() {
        return codice_gioco;
    }

    public void setCodice_gioco(String codice_gioco) {
        this.codice_gioco = codice_gioco;
    }

    public String getNome_genere() {
        return nome_genere;
    }

    public void setNome_genere(String nome_genere) {
        this.nome_genere = nome_genere;
    }

}
