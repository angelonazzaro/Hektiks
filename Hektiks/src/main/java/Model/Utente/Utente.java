package Model.Utente;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Utente implements Serializable {

    @Serial
    private static final long serialVersionUID = 6645001885280633923L;

    private String email;
    private String nome;
    private String cognome;
    private String username;
    private String password_utente;
    private Date data_registrazione;
    private boolean ruolo;
    private double saldo;
    private String biografia;

    public Utente() {

    }

    @Override
    public String toString() {
        return "Utente{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", password_utente='" + password_utente + '\'' +
                ", data_registrazione=" + data_registrazione +
                ", ruolo=" + ruolo +
                ", saldo=" + saldo +
                ", biografia='" + biografia + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_utente() {
        return password_utente;
    }

    public void setPassword_utente(String password_utente) {
        this.password_utente = password_utente;
    }

    public Date getData_registrazione() {
        return data_registrazione;
    }

    public void setData_registrazione(Date data_registrazione) {
        this.data_registrazione = data_registrazione;
    }

    public boolean isRuolo() {
        return ruolo;
    }

    public void setRuolo(boolean ruolo) {
        this.ruolo = ruolo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
