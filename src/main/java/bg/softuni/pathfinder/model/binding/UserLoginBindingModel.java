package bg.softuni.pathfinder.model.binding;

import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    @Size(min = 2)
    private String username;

    @Size(min = 2)
    private String password;

    public UserLoginBindingModel() {
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
}
