package bg.softuni.pathfinder.model.service;

import bg.softuni.pathfinder.model.entities.CategoryEntity;
import bg.softuni.pathfinder.model.entities.PictureEntity;
import bg.softuni.pathfinder.model.entities.UserEntity;
import bg.softuni.pathfinder.model.enums.LevelEnum;

import java.util.List;
import java.util.Set;

public class RouteServiceModel extends BaseServiceModel {
    private Long id;
    private String description;

    private String gpxCoordinates;

    private LevelEnum level;

    private String name;

    private String videoUrl;

    private UserEntity author;

    private List<CategoryEntity> categories;

    private Set<PictureEntity> pictures;

    public RouteServiceModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
