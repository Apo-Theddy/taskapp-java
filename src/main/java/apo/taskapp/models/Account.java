package apo.taskapp.models;

import lombok.Getter;
import lombok.Setter;

public class Account {

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String creationDate;

    @Getter
    @Setter
    private String deletedDate;

    @Getter
    @Setter
    private String updatedDate;

    @Getter
    @Setter
    private User user;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account(String email, String password, String creationDate, String deletedDate,
            String updatedDate, User user) {
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
        this.deletedDate = deletedDate;
        this.updatedDate = updatedDate;
        this.user = user;
    }
}
