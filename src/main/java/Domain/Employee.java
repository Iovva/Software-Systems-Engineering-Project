package Domain;

import java.util.Objects;

public class Employee {

    private String cnp;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private EmployeeRank rank;

    public Employee(String cnp, String username,
                    String password, String firstName,
                    String lastName, String rank) {

        this.cnp = cnp;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        setRank(rank);
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeRank getRank() {
        return rank;
    }

    public String getRankAsString() {
        return rank.toString();
    }

    public void setRank(String rank) {
        if (Objects.equals(rank, "ADMINISTRATOR"))
            this.rank = EmployeeRank.ADMINISTRATOR;
        else if (Objects.equals(rank, "BOSS"))
            this.rank = EmployeeRank.BOSS;
        else if (Objects.equals(rank, "WORKER"))
            this.rank = EmployeeRank.WORKER;
        else
            this.rank = EmployeeRank.INVALID;
    }

    public void setRank(EmployeeRank rank){
        this.rank = rank;
    }

    public void startWork(){

    }

    public void leaveWork(){

    }

    public void finishTask(){

    }
}
