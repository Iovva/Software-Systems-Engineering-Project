package Domain;

import java.util.Objects;

public abstract class Validator {

    public static void validateEmployee(Employee employee) throws DomainException {
        String error = "";

        if (Objects.equals(employee.getCnp(), ""))
            error += "Invalid CNP!\n";
        if (Objects.equals(employee.getUsername(), ""))
            error += "Invalid username!\n";
        if (Objects.equals(employee.getPassword(), ""))
            error += "Invalid password!\n";
        if (Objects.equals(employee.getFirstName(), ""))
            error += "Invalid first name!\n";
        if (Objects.equals(employee.getLastName(), ""))
            error += "Invalid last name!\n";
        if (Objects.equals(employee.getRank(), EmployeeRank.INVALID))
            error += "Invalid rank!\n";

        if (!error.equals("")){
            throw new DomainException("Employee validation failed!\n\n" + error);
        }
    }

    public static void validateTask(Task task) throws DomainException {
        String error = "";

        if (task.getId() < 0){
            error += "Invalid id!\n";
        }
        if (Objects.equals(task.getTaskName(), "")){
            error += "Invalid task name!\n";
        }
        if (Objects.equals(task.getTaskName(), "")){
            error += "Invalid task description!\n";
        }
        if (Objects.equals(task.getStatus(), TaskStatus.INVALID)){
            error += "Invalid task status!\n";
        }
        if (!error.equals("")){
            throw new DomainException("Task validation failed!\n\n" + error);
        }
    }
}
