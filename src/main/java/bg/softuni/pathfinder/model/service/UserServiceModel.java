package bg.softuni.pathfinder.model.service;

import bg.softuni.pathfinder.model.entities.RoleEntity;
import bg.softuni.pathfinder.model.enums.LevelEnum;

import javax.persistence.*;
import java.util.List;

public class UserServiceModel extends BaseServiceModel {
    private String username;

    private String password;

    private String email;

    private String fullName;

    private int age;

    private LevelEnum level;

    private List<RoleEntity> roles;

    public UserServiceModel() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
