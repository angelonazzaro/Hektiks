package Model.Storage;

import javax.sql.DataSource;

/**
 * Classe astratta che permette di far ereditare il DataSource a tutti i DAO
 */

public abstract class SQLDAO {

    protected final DataSource source;

    public SQLDAO(DataSource source) {
        this.source = source;
    }
}
