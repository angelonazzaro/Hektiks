package Utils;

import java.util.List;

public class JSONResponse<T> {
    private String type;
    private String message;
    private List<T> data;

    public JSONResponse(String type) {
        this.type = type;
    }

    public JSONResponse(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public JSONResponse(String type, List<T> data) {
        this.type = type;
        this.data = data;
    }

    public JSONResponse(String type, String message, List<T> data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
