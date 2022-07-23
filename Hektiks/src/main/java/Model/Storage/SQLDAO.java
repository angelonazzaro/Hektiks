package Model.Storage;

import javax.sql.DataSource;

/**
 * Classe astratta che permette di far ereditare il DataSource a tutti i DAO
 * in modo tale da evitare che ogni DAO debba avere un DataSource come
 * field
 */

public abstract class SQLDAO {

    protected final DataSource source;

    public SQLDAO(DataSource source) {
        this.source = source;
    }
}
