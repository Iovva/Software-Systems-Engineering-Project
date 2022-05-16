package Repository;

import Domain.Employee;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class EmployeeRepo {

    private final JdbcUtils dbUtils;



    public EmployeeRepo(Properties properties) {
        dbUtils = new JdbcUtils(properties);
    }

    public List<Employee> getAll() throws RepositoryException {
        List<Employee> list = new ArrayList<>();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement prepareStatement = con.prepareStatement(
                "select * from employees");
             ResultSet resultSet = prepareStatement.executeQuery()) {
            while (resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getString("cnp"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("rank")
                );
                list.add(employee);
            }
        }
        catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
        return list;
    }

    public void save(Employee employee) throws RepositoryException {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "insert into employees (cnp, username, password, firstName, lastName, rank) " +
                        "values (?,?,?,?,?,?)"
        )){
            preparedStatement.setString(1, employee.getCnp());
            preparedStatement.setString(2, employee.getUsername());
            preparedStatement.setString(3, employee.getPassword());
            preparedStatement.setString(4, employee.getFirstName());
            preparedStatement.setString(5, employee.getLastName());
            preparedStatement.setString(6, employee.getRankAsString());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }

    public void update(Employee employee) throws RepositoryException {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("" +
                "update employees set username = ?, password = ?, firstName = ?, lastName = ? where cnp = ?")){
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setString(5, employee.getCnp());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }

    public void delete(String cnp)  throws RepositoryException{
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("" +
                "delete from employees  where cnp = ?")){
            preparedStatement.setString(1, cnp);

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }
}
