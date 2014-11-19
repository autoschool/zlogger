package zlogger.delete_this_example;

import org.hibernate.validator.constraints.NotEmpty;

public class FormDTO {

    @NotEmpty
    private String messageFromUser;

    public String getMessageFromUser() {
        return messageFromUser;
    }

    public void setMessageFromUser(String messageFromUser) {
        this.messageFromUser = messageFromUser;
    }

    @Override
    public String toString() {
        return "FromDTO [messageFromUser=" + messageFromUser + "]";
    }
}
