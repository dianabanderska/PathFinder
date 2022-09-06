package bg.softuni.pathfinder.model.entities;

import bg.softuni.pathfinder.model.enums.LevelEnum;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "routes")
public class RouteEntity extends BaseEntity{

    @Lob
    @Column(columnDefinition = "text")
    private String description;

    @Lob
    @Column(columnDefinition = "longtext")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private LevelEnum level;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UserEntity author;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "routes_categories",
    joinColumns = @JoinColumn(name = "route_entity_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "categories_id", referencedColumnName = "id"))
    private List<CategoryEntity> categories;

    @OneToMany(mappedBy = "route", targetEntity = PictureEntity.class, fetch = FetchType.EAGER)
    private Set<PictureEntity> pictures;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    public RouteEntity() {
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

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
}
