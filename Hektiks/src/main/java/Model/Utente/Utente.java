package Model.Utente;

import java.util.Date;

public class Utente {

    private String email;
    private String username;
    private String password_utente;
    private Date data_registrazione;
    private boolean ruolo;
    private double saldo;
    private String biografia;

    public Utente(String email, String username, String password_utente, Date data_registrazione,
                  boolean ruolo, double saldo, String biografia) {

        this.email = email;
        this.username = username;
        this.password_utente = password_utente;
        this.data_registrazione = data_registrazione;
        this.ruolo = ruolo;
        this.saldo = saldo;
        this.biografia = biografia;
    }

    public Utente(){

        this("", "", "", new Date(), false, 0, "");
    }

    @Override
    public String toString() {
        return "Utente{" +
                "email='" + email + '\'' +
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
