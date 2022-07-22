package Model.Utente;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;
import Utils.PasswordEncrypt;

import java.io.Serial;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;

/**
 * Classe che rappresenta il Bean dell'utente
 * fornisce metodi getter, setter, toString e toHashMap
 **/

public class Utente extends GenericBean implements Serializable, IEntity {

    @Serial
    private static final long serialVersionUID = 6645001885280633923L;

    private String email;
    private String nome;
    private String cognome;
    private String username;
    private String password_utente;
    private Date data_registrazione;
    private boolean ruolo = false;
    private double saldo = 0;
    private String profile_pic = "";

    @Override
    public String toString() {
        return "Utente{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ",profile_pic='" + profile_pic + '\'' +
                ", password_utente='" + password_utente + '\'' +
                ", data_registrazione=" + data_registrazione +
                ", ruolo=" + ruolo +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public HashMap<String, ?> toHashMap() {

        return new HashMap<>() {
            {
                put("email", email);
                put("nome", nome);
                put("cognome", cognome);
                put("username", username);
                put("password_utente", password_utente);
                put("data_registrazione", data_registrazione.toString());
                put("ruolo", ruolo);
                put("saldo", saldo);
            }
        };
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

        try {
            this.password_utente = PasswordEncrypt.sha1(password_utente);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
