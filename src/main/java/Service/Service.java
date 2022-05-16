package Service;

import Domain.DomainException;
import Domain.Employee;
import Domain.Task;
import Domain.Validator;
import Repository.EmployeeRepo;
import Repository.RepositoryException;
import Repository.TaskRepo;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Service {

    private final TaskRepo taskRepo;
    private final EmployeeRepo employeeRepo;

    public Service(Properties properties){
        taskRepo = new TaskRepo(properties);
        employeeRepo = new EmployeeRepo(properties);
    }

    public List<Employee> getEmployees() throws ServiceException {
        try {
            return employeeRepo.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Couldn't get employees!\n" + e.getMessage());
        }
    }

    public void saveEmployee(String cnp, String username, String password, String firstName, String lastName, String rank) throws ServiceException {
        try{
            Employee employee = new Employee(cnp, username, password, firstName, lastName, rank);
            Validator.validateEmployee(employee);
            employeeRepo.save(employee);

        } catch (RepositoryException e) {
            throw new ServiceException("Couldn't save employee!\nCheck if the CNP and username are unique!" +
                    "\n\nAdditional info:\n" + e.getMessage());
        } catch (DomainException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateEmployee(String cnp, String username, String password, String firstName, String lastName, String rank) throws ServiceException {
        try{
            Employee employee = new Employee(cnp, username, password, firstName, lastName, rank);
            Validator.validateEmployee(employee);
            employeeRepo.update(employee);

        } catch (RepositoryException e) {
            throw new ServiceException("Couldn't update employee!\nCheck if the user exists and the CNP is correct!" +
                    "\n\nAdditional info:\n" + e.getMessage());
        } catch (DomainException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteEmployee(String cnp) throws ServiceException {
        try {
            employeeRepo.delete(cnp);
        } catch (RepositoryException e) {
            throw new ServiceException("Couldn't delete employee!\nCheck if the CNP is correct!"  +
                    "\n\nAdditional info:\n" + e.getMessage());
        }
    }

    public List<Task> getTasks() throws ServiceException {
        try {
            return taskRepo.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Couldn't get tasks!\n" + e.getMessage());
        }
    }

    public Employee login(String username, String password) throws ServiceException {
        try {
            List<Employee> employees = getEmployees();
            for (Employee e : employees){
                if (Objects.equals(e.getUsername(), username)){
                    if (Objects.equals(e.getPassword(), password))
                        return e;
                    else
                        return null;
                }
            }
        } catch (ServiceException e) {
            throw new ServiceException("Couldn't log in!\n" + e.getMessage());
        }
        return null;
    }

    public void saveTask(int id, String taskName, String description, String startTime, String endTime, String status) throws ServiceException {
        try{
            Task task = new Task(id, taskName, description, startTime, endTime, status);
            Validator.validateTask(task);
            taskRepo.save(task);

        } catch (RepositoryException e) {
            throw new ServiceException("Couldn't save task!\n" + e.getMessage());
        } catch (DomainException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateTask(int id, String taskName, String description, String startTime, String endTime, String status) throws ServiceException {
        try{
            Task task = new Task(id, taskName, description, startTime, endTime, status);
            Validator.validateTask(task);
            taskRepo.update(task);
        } catch (RepositoryException e) {
            throw new ServiceException("Couldn't update task!\n" + e.getMessage());
        } catch (DomainException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteTask(int id) throws ServiceException {
        try {
            taskRepo.delete(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Couldn't delete employee!\n" + e.getMessage());
        }
    }
}
