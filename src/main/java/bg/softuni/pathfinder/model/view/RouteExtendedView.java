package bg.softuni.pathfinder.model.view;

import bg.softuni.pathfinder.model.entities.PictureEntity;
import bg.softuni.pathfinder.model.entities.UserEntity;
import bg.softuni.pathfinder.model.enums.LevelEnum;

import java.util.Set;

public class RouteExtendedView {

    private Long id;
    private String name;
    private String description;
    private String videoUrl;
    private LevelEnum level;
    private String authorName;
    private Set<PictureEntity> pictures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
