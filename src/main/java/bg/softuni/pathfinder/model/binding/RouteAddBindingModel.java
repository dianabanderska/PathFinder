package bg.softuni.pathfinder.model.binding;

import bg.softuni.pathfinder.model.enums.CategoryEnum;
import bg.softuni.pathfinder.model.enums.LevelEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class RouteAddBindingModel {
    @NotNull(message = "Name field should not be null!")
    @Size(min=4, message = "Route name length must be more than or equal to 4 characters!")
    private String name;
    @NotNull
    @Size(min=6)
    private String description;
    private MultipartFile gpxCoordinates;
    @NotNull
    private LevelEnum level;
    @NotNull
    @Size(min = 11, max = 11)
    private String videoUrl;
    private List<CategoryEnum> categories;

    public RouteAddBindingModel() {
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

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<CategoryEnum> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEnum> categories) {
        this.categories = categories;
    }
}
