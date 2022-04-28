package Model.Genere;

import java.io.Serial;
import java.io.Serializable;

public class Genere implements Serializable {

    @Serial
    private static final long serialVersionUID = -2680613387299216950L;

    private String nome_genere;

    public Genere(String nome_genere) {

        this.nome_genere = nome_genere;
    }

    public Genere() {

        this("");
    }

    @Override
    public String toString() {
        return "Generi{" +
                "nome_genere='" + nome_genere + '\'' +
                '}';
    }

    public String getNome_genere() {
        return nome_genere;
    }

    public void setNome_genere(String nome_genere) {
        this.nome_genere = nome_genere;
    }
}
