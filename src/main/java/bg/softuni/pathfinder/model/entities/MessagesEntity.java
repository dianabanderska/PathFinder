package bg.softuni.pathfinder.model.entities;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class MessagesEntity extends BaseEntity{

    @Column(nullable = false, name = "date_time")
    private LocalDateTime dateTime;

    @Lob
    @Column(name = "text_content", columnDefinition = "text")
    private String textContent;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private UserEntity recipient;

    public MessagesEntity() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public UserEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(UserEntity recipient) {
        this.recipient = recipient;
    }
}
