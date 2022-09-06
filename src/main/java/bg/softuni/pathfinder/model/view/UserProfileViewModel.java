package bg.softuni.pathfinder.model.view;

import bg.softuni.pathfinder.model.enums.LevelEnum;

public class UserProfileViewModel {

    private String username;
    private String fullName;
    private int age;
    private LevelEnum level;

    public UserProfileViewModel() {
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
}
