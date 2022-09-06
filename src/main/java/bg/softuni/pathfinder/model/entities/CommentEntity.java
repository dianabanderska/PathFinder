package bg.softuni.pathfinder.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @Column(nullable = false)
    private Boolean approved;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(name = "text_content", nullable = false, columnDefinition = "text")
    private String textContent;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private RouteEntity route;

    public CommentEntity() {
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
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
