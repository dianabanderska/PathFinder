package bg.softuni.pathfinder.model.entities;

import bg.softuni.pathfinder.model.enums.LevelEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(nullable = false)
    private String fullName;

    private int age;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_entity_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private List<RoleEntity> roles;

    public UserEntity() {
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

    public void setFullName(String fullname) {
        this.fullName = fullname;
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

