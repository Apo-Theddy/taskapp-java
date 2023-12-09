package apo.taskapp.controllers;

import apo.taskapp.models.Account;
import apo.taskapp.models.User;
import apo.taskapp.services.AccountService;

public class AccountController {
    private final AccountService accountService;

    public AccountController() {
        accountService = new AccountService();
    }

    public User signIn(Account account) {
        User user = accountService.signIn(account.getEmail(), account.getPassword());
        return user;
    }

}
