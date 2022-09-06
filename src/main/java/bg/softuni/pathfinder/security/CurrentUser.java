package bg.softuni.pathfinder.security;

import bg.softuni.pathfinder.model.entities.RoleEntity;
import bg.softuni.pathfinder.model.enums.RoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    public CurrentUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
