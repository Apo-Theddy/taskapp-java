package apo.taskapp.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import apo.taskapp.database.SQLConnection;
import apo.taskapp.models.Account;
import apo.taskapp.models.User;

public class AccountService {
    private final Connection connection;

    public AccountService() {
        connection = SQLConnection.getConnection();
    }

    public User signIn(String email, String password) {
        User user = null;

        try {
            String query = "SELECT * FROM FN_GetUserAccount(?,?)";
            PreparedStatement pStatement = connection.prepareStatement(query);

            pStatement.setString(1, email);
            pStatement.setString(2, password);

            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                int userID = results.getInt("UserID");
                String names = results.getString("Names");
                String lastnames = results.getString("Lastnames");
                String birthday = results.getString("Birthday");
                Account account = new Account(email, password);
                user = new User(userID, names, lastnames, birthday, account);
            }
        } catch (SQLException sqlex) {
            System.out.println("Ocurrio un error al obtener los datos de la cuenta: " + sqlex.getMessage());
        }

        return user;
    }

    public boolean createNewAccount(Account account) {
        boolean accountIsCreated = false;
        try {
            String query = "{call SP_CreateNewAccount(?,?,?)}";
            CallableStatement cStatement = connection.prepareCall(query);
            cStatement.setString(1, account.getEmail());
            cStatement.setString(2, account.getPassword());
            cStatement.setInt(3, account.getUser().getUserID());
            cStatement.execute();
            accountIsCreated = true;
        } catch (SQLException sqlex) {
            System.out.println("Ocurrio un error al crear la cuenta: " + sqlex.getMessage());
        }
        return accountIsCreated;
    }

    public boolean updateAccount(Account account) {
        boolean accountIsUpdated = false;
        try {
            String query = "{call SP_UpdateAccount(?,?)}";
            CallableStatement cStatement = connection.prepareCall(query);
            cStatement.setString(1, account.getEmail());
            cStatement.setString(2, account.getPassword());
            cStatement.execute();
            accountIsUpdated = true;
        } catch (SQLException sqlex) {
            System.out.println("Ocurrio un error al actualizar la cuenta: " + sqlex.getMessage());
        }
        return accountIsUpdated;
    }

}
