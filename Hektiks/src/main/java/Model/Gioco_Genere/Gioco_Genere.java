package Model.Gioco_Genere;

import java.io.Serial;
import java.io.Serializable;

public class Gioco_Genere implements Serializable {

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
