package apo.taskapp.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import apo.taskapp.database.SQLConnection;
import apo.taskapp.models.Task;
import apo.taskapp.models.User;

public class TaskService {

    private final Connection connection;

    public TaskService() {
        connection = SQLConnection.getConnection();
    }

    public ArrayList<Task> getTasks(User user) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            String query = "SELECT * FROM FN_GetTaskByUserID(?)";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, user.getUserID());
            ResultSet results = pStatement.executeQuery();
            while (results.next()) {
                int taskID = results.getInt("TaskID");
                String title = results.getString("Title");
                String content = results.getString("Content");
                String creationDate = results.getDate("CreationDate").toString();
                tasks.add(new Task(taskID, title, content, creationDate, null));
            }
        } catch (SQLException sqlex) {
            System.out.println("Ocurrio un error al obtener las tareas: " + sqlex.getMessage());
        }

        return tasks;
    }

    public boolean createNewTask(Task task, User user) {
        boolean taskIsCreated = false;
        try {
            String query = "{call SP_CreateNewTask(?,?,?)}";
            CallableStatement cStatement = connection.prepareCall(query);
            cStatement.setString(1, task.getTitle());
            cStatement.setString(2, task.getContent());
            cStatement.registerOutParameter(3, Types.INTEGER);

            cStatement.execute();

            int taskID = cStatement.getInt(3);
            query = "INSERT INTO Users_Tasks VALUES(?,?)";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, user.getUserID());
            pStatement.setInt(2, taskID);
            pStatement.executeQuery();
            taskIsCreated = true;
        } catch (SQLException sqlex) {
            System.out.println("Ocurrio un error al crear una tarea: " + sqlex.getMessage());
        }

        return taskIsCreated;
    }

    public boolean deleteTask(Task task) {
        boolean taskIsDeleted = false;
        try {
            String query = "{call SP_DeleteTask(?)}";
            CallableStatement cStatement = connection.prepareCall(query);
            cStatement.setInt(1, task.getTaskID());
            cStatement.execute();
            taskIsDeleted = true;
        } catch (SQLException sqlex) {
            System.out.println("Ocurrio un error al eliminar la tarea: " + sqlex.getMessage());
        }
        return taskIsDeleted;
    }

    public boolean updateTask(Task task) {
        boolean taskIsUpdated = false;
        try {
            String query = "{call SP_UpdateTask(?,?,?)}";
            CallableStatement cStatement = connection.prepareCall(query);
            cStatement.setInt(1, task.getTaskID());
            cStatement.setString(2, task.getTitle());
            cStatement.setString(3, task.getContent());
            cStatement.execute();
            taskIsUpdated = true;
        } catch (SQLException sqlex) {
            System.out.println("Ocurrio un error al actualizar la tarea: " + sqlex.getMessage());
        }

        return taskIsUpdated;
    }

}
