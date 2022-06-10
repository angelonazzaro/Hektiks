package Model.GenericBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenericBean {

    private HashMap<String, List<Object>> entries;

    public GenericBean(HashMap<String, List<Object>> entries) {
        this.entries = entries;
    }

    public void addEntry(String key, Object value) {

        if (!this.entries.containsKey(key)) {

            ArrayList<Object> tmp = new ArrayList<Object>();
            tmp.add(value);
            entries.put(key, tmp);

        } else {

            addEntryValue(key, value);
        }
    }

    public void addEntryValue(String key, Object value) {

        if (this.entries.containsKey(key)) {

            List<Object> tmp = entries.get(key);
            tmp.add(value);
        } else {

            addEntry(key, value);
        }

    }

    @Override
    public String toString() {
        return "GenericBean{" +
                "entries=" + entries +
                '}';
    }

    public void setEntries(HashMap<String, List<Object>> entries) {
        this.entries = entries;
    }

    public HashMap<String, List<Object>> getEntries() {
        return entries;
    }

    public GenericBean() {
        this.entries = new HashMap<>();
    }
}
