package Model.Utente;

import Model.Storage.GenericBean;
import Model.Storage.IEntity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;

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

    private String biografia = "";

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
                put("biografia", biografia);
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
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password_utente.getBytes(StandardCharsets.UTF_8));
            this.password_utente = password_utente;
            this.password_utente = String.format("%040x", new
                    BigInteger(1, digest.digest()));
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
