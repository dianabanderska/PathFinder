package bg.softuni.pathfinder.model.view;

import bg.softuni.pathfinder.model.entities.PictureEntity;
import bg.softuni.pathfinder.model.enums.LevelEnum;

import java.util.Set;

public class RouteMiniViewModel {
    private Long id;
    private String name;

    private String description;

    private PictureView picture;

    public RouteMiniViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PictureView getPicture() {
        return picture;
    }

    public void setPicture(PictureView picture) {
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
