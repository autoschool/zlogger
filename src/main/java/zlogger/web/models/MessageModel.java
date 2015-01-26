package zlogger.web.models;

import java.io.Serializable;

public class MessageModel implements Serializable {

    String message;

    public MessageModel(String message) {
        this.message = message;
    }

    public MessageModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
