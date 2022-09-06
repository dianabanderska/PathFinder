package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.entities.CategoryEntity;
import bg.softuni.pathfinder.model.enums.CategoryEnum;
import bg.softuni.pathfinder.model.service.CategoryServiceModel;
import bg.softuni.pathfinder.repository.CategoryRepository;
import bg.softuni.pathfinder.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryEntity findCategoryByName(CategoryEnum category) {
        return this.categoryRepo.findByName(category)
                .orElse(null);
    }
}
