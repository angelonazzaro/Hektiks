package Model.Storage;

import java.util.List;

public class GenericBean {
    protected List<Object> join;

    public List<Object> getJoin() { return join; }

    public void setJoin(List<Object> join) { this.join = join; }

    public void addToJoin(Object obj) { this.join.add(obj); }
}
