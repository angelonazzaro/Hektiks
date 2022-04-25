package Model.Storage;

import javax.sql.DataSource;

public abstract class SQLDAO {

    protected final DataSource source;

    public SQLDAO(DataSource source) {

        this.source = source;
    }
}
