package apo.taskapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static final String USER = "sa";
    private static final String PASSWORD = "Sql@S3cur3@Password";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=lab14;encrypt=true;trustServerCertificate=true;";

    static {
        new com.microsoft.sqlserver.jdbc.SQLServerDriver();
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successfully");
            return connection;
        } catch (SQLException sqlex) {
            System.out.println("Error: " + sqlex.getMessage());
            return null;
        }
    }

}
