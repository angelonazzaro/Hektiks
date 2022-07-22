package Model.Storage;

import java.util.HashMap;

/**
 * Interfaccia che implementano tutti i bean
 * il metodo deve fornire gli attributi di un bean
 * sottoforma di hashmap in modo da semplificare l'inserimento
 * e la modifica degli attributi in una query
 */
public interface IEntity {

    HashMap<String, ?> toHashMap();
}
