package Model.Genere;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe che rappresenta il Bean del genere
 * fornisce metodi getter, setter, toString e toHashMap
 **/

public class Genere extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = -2680613387299216950L;

    private String nome_genere;

    @Override
    public String toString() {
        return "Generi{" +
                "nome_genere='" + nome_genere + '\'' +
                '}';
    }

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("nome_genere", nome_genere);
            }
        };
    }

    public String getNome_genere() {
        return nome_genere;
    }

    public void setNome_genere(String nome_genere) {
        this.nome_genere = nome_genere;
    }


}
