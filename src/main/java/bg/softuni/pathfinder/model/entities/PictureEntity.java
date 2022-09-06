package bg.softuni.pathfinder.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity{

    private String title;

    @Column(columnDefinition = "text")
    private String url;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private RouteEntity route;

    public PictureEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }
}
