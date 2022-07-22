package Model.Storage;

/**
 * Record per utilizzare delle costanti letterali di ogni tabella
 * del database per evitare
 * di scrivere più volte la stessa tabella rischiando di sbagliare.
*/

public record Entities() {

    public static final String UTENTI = "Utenti";
    public static final String GENERI = "Generi";
    public static final String GIOCHI = "Giochi";
    public static final String CARRELLI = "Carrelli";
    public static final String GIOCHI_GENERE = "Giochi_Genere";
    public static final String PRODOTTI = "Prodotti";
    public static final String RECENSIONI = "Recensioni";
    public static final String GIFTCARDS = "GiftCards";
    public static final String ORDINI = "Ordini";
    public static final String PAGAMENTI = "Pagamenti";
    public static final String PRODOTTI_ORDINI = "Prodotti_Ordini";
}
