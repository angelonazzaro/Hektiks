package Model.Storage;

import java.util.List;

/**
 * Bean generico per la gestione delle query con le join ogni Bean
 * usato in una query eredita la lista di oggetti contentente gli altri Bean
 * risultanti dalla query con join
 **/

public class GenericBean {
    protected List<Object> join;

    public List<Object> getJoin() {
        return join;
    }

    public void setJoin(List<Object> join) {
        this.join = join;
    }

    public void addToJoin(Object obj) {
        this.join.add(obj);
    }
}
