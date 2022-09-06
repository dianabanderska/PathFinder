package bg.softuni.pathfinder.model.entities;

import bg.softuni.pathfinder.model.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategoryEnum name;

    @Lob
    @Column(columnDefinition = "text")
    private String description;

    public CategoryEntity() {
    }

    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
