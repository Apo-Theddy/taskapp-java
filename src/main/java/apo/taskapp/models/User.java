package apo.taskapp.models;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    @Setter
    private int userID;

    @Getter
    @Setter
    private String names;

    @Getter
    @Setter
    private String lastnames;

    @Getter
    @Setter
    private String birthday;

    @Getter
    @Setter
    private Account account;

    public User(String names, String lastnames, String birthday) {
        this.names = names;
        this.lastnames = lastnames;
        this.birthday = birthday;
    }

    public User(int userID, String names, String lastnames, String birthday, Account account) {
        this.userID = userID;
        this.names = names;
        this.lastnames = lastnames;
        this.birthday = birthday;
        this.account = account;
    }

}
