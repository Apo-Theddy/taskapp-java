package apo.taskapp.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import apo.taskapp.database.SQLConnection;
import apo.taskapp.models.User;

public class UserService {
    private final Connection connection;
    private final AccountService accountService;

    public UserService() {
        connection = SQLConnection.getConnection();
        accountService = new AccountService();
    }

    boolean addNewUser(User user) {
        boolean userIsCreated = false;
        try {
            String query = "{call SP_CreateNewUser(?,?,?)}";
            CallableStatement cStatement = connection.prepareCall(query);
            cStatement.setString(1, user.getNames());
            cStatement.setString(2, user.getLastnames());
            cStatement.setString(3, user.getBirthday());
            cStatement.execute();
            boolean accountIsCreated = accountService.createNewAccount(user.getAccount());
            if (!accountIsCreated) {
                throw new SQLException("Ocurrio un error al crear la cuenta");
            }
            userIsCreated = true;
        } catch (SQLException sqlex) {
            System.out.println("Error al crear el usuario: " + sqlex.getMessage());
        }
        return userIsCreated;
    }

    boolean deleteUserByID(User user) {
        boolean userIsDeleted = false;

        try {
            String query = "{call SP_DeleteUserByID(?)}";
            CallableStatement cStatement = connection.prepareCall(query);
            cStatement.setInt(1, user.getUserID());
            cStatement.execute();
            userIsDeleted = true;
        } catch (SQLException sqlex) {
            System.out.println("Ocurrio un error al eliminar el usuario: " + sqlex.getMessage());
        }
        return userIsDeleted;
    }

    boolean updateUser(User user) {
        boolean userIsUpdated = false;
        try {
            String query = "{call SP_UpdateUserByID(?,?,?,?)}";
            CallableStatement cStatement = connection.prepareCall(query);
            cStatement.setInt(1, user.getUserID());
            cStatement.setString(2, user.getNames());
            cStatement.setString(3, user.getLastnames());
            cStatement.setString(4, user.getBirthday());
            cStatement.execute();
            userIsUpdated = true;
        } catch (SQLException sqlex) {
            System.out.println("Ocurrio un error al actualizar el usuario: " + sqlex.getMessage());
        }
        return userIsUpdated;
    }

}
