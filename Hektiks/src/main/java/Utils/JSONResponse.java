package Utils;

import java.util.List;

public class JSONResponse<T> {
    private String type;
    private String value;
    private List<T> data;

    public JSONResponse(String type) {
        this.type = type;
    }

    public JSONResponse(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public JSONResponse(String type, List<T> data) {
        this.type = type;
        this.data = data;
    }

    public JSONResponse(String type, String value, List<T> data) {
        this.type = type;
        this.value = value;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
