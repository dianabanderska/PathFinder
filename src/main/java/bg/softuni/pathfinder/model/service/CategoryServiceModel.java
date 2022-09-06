package bg.softuni.pathfinder.model.service;

import bg.softuni.pathfinder.model.enums.CategoryEnum;

public class CategoryServiceModel {
    private CategoryEnum name;

    private String description;

    public CategoryServiceModel() {
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
