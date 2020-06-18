package bean;

import java.io.Serializable;

public class User implements Serializable{
    private String phone;
    private String firstName;
    private String lastName;
    private String role;
    private String status;

    public User() {
        this.phone = "";
        this.firstName = "";
        this.lastName = "";
        this.role = "";
        this.status = "";
    }

    public User(String phone, String firstName, String lastName, String role, String status) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
