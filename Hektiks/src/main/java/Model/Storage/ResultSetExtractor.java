package Model.Storage;

import Model.Carrello.CarrelloExtractor;
import Model.Genere.GenereExtractor;
import Model.GiftCard.GiftCardExtractor;
import Model.Gioco.GiocoExtractor;
import Model.Gioco_Genere.Gioco_GenereExtractor;
import Model.Ordine.OrdineExtractor;
import Model.Pagamento.PagamentoExtractor;
import Model.Prodotto.ProdottoExtractor;
import Model.Prodotto_Ordine.Prodotto_OrdineExtractor;
import Model.Recensione.RecensioneExtractor;
import Model.Utente.UtenteExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Storage.Entities.*;

/**
 * Interfaccia che definisce il prototipo di un metodo generico che ogni
 * Bean deve implementare per la gestione dei risultati di una query.
 **/

public interface ResultSetExtractor<B> {

    /**
     * Estrae i field di un ResultSet e restituisce un bean corrsipondente.
     **/

    B extract(ResultSet resultSet, String... tables) throws SQLException;

    /**
     * Restituisce un Extractor corrispondente alla tabella passata come parametro.
     **/

    default ResultSetExtractor<?> findExtractor(String table) {
        return switch (table) {
            case CARRELLI -> new CarrelloExtractor();
            case GENERI -> new GenereExtractor();
            case GIFTCARDS -> new GiftCardExtractor();
            case GIOCHI -> new GiocoExtractor();
            case GIOCHI_GENERE -> new Gioco_GenereExtractor();
            case ORDINI -> new OrdineExtractor();
            case PAGAMENTI -> new PagamentoExtractor();
            case PRODOTTI -> new ProdottoExtractor();
            case PRODOTTI_ORDINI -> new Prodotto_OrdineExtractor();
            case RECENSIONI -> new RecensioneExtractor();
            case UTENTI -> new UtenteExtractor();
            default -> null;
        };
    }
}
