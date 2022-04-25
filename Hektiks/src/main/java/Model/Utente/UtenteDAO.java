package Model.Utente;

import java.util.List;
import java.util.Optional;

public interface UtenteDAO<E extends Exception> {

    List<Utente> fetchUtenti(int start, int end) throws E;

    Optional<Utente> fetchUtente(String email) throws E;

    boolean createUtente(Utente utente) throws E;

    boolean updateUtente(Utente utente) throws E;

    boolean deleteUtente(String email) throws E;
}
