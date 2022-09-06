package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.entities.CategoryEntity;
import bg.softuni.pathfinder.model.enums.CategoryEnum;
import bg.softuni.pathfinder.model.service.CategoryServiceModel;

import java.util.Optional;

public interface CategoryService {

    CategoryEntity findCategoryByName(CategoryEnum category);
}
