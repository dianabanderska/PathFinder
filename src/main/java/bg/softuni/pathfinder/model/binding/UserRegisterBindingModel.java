package bg.softuni.pathfinder.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
    @NotNull
    @Size(min = 2)
    private String username;
    @NotNull
    @Size(min = 2)
    private String fullName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Positive
    private int age;
    @NotNull
    @Size(min = 2)
    private String password;
    @NotNull
    @Size(min = 2)
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
