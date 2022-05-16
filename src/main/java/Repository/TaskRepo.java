package Repository;

import Domain.Employee;
import Domain.Task;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TaskRepo {

    private final JdbcUtils dbUtils;


    public TaskRepo(Properties properties) {
        dbUtils = new JdbcUtils(properties);
    }

    public List<Task> getAll() throws RepositoryException {
        List<Task> list = new ArrayList<>();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement prepareStatement = con.prepareStatement(
                "select * from tasks");
             ResultSet resultSet = prepareStatement.executeQuery()) {
            while (resultSet.next()){
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("taskName"),
                        resultSet.getString("description"),
                        resultSet.getString("startTime"),
                        resultSet.getString("endTime"),
                        resultSet.getString("status")
                );
                list.add(task);
            }
        }
        catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
        return list;
    }

    public void save(Task task) throws RepositoryException {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "insert into tasks (id, taskName, description, startTime, endTime, status) " +
                        "values (?,?,?,?,?,?)"
        )){
            preparedStatement.setInt(1, task.getId());
            preparedStatement.setString(2, task.getTaskName());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setString(4, task.getStartTimeAsString());
            preparedStatement.setString(5, task.getEndTimeAsString());
            preparedStatement.setString(6, task.getStatusAsString());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }

    public void update(Task task) throws RepositoryException {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("" +
                "update tasks set taskName = ?, description = ?, startTime = ?, endTime = ?, status = ? where id = ?")){
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getStartTimeAsString());
            preparedStatement.setString(4, task.getEndTimeAsString());
            preparedStatement.setString(5, task.getStatusAsString());
            preparedStatement.setInt(6, task.getId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }

    public void delete(int id)  throws RepositoryException{
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("" +
                "delete from tasks  where id = ?")){
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }
}
