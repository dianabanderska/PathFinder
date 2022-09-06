package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.binding.RouteAddBindingModel;
import bg.softuni.pathfinder.model.entities.CategoryEntity;
import bg.softuni.pathfinder.model.entities.RouteEntity;
import bg.softuni.pathfinder.model.entities.UserEntity;
import bg.softuni.pathfinder.model.enums.CategoryEnum;
import bg.softuni.pathfinder.model.service.RouteServiceModel;
import bg.softuni.pathfinder.model.service.UserServiceModel;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.CategoryService;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final UserService userService;


    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper,
                            CategoryService categoryService, UserService userService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public List<RouteServiceModel> getAllRoutes() {
        return this.routeRepository.findAll()
                .stream()
                .map(routeEntity -> this.modelMapper.map(routeEntity, RouteServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RouteServiceModel findById(Long id) {
        return this.routeRepository.findById(id)
                .map(entity -> this.modelMapper.map(entity, RouteServiceModel.class))
                .orElse(null);
    }

    @Override
    public RouteServiceModel saveRoute(RouteAddBindingModel routeAddBindingModel) throws IOException {

        RouteEntity entityToSave = this.modelMapper.map(routeAddBindingModel, RouteEntity.class);

        //Set Categories
        List<CategoryEntity> categoryList = new ArrayList<>();
        for (CategoryEnum category :routeAddBindingModel.getCategories()) {
            CategoryEntity categoryEntity = this.categoryService.findCategoryByName(category);
            categoryList.add(categoryEntity);
        }
        entityToSave.setCategories(categoryList);

        //SetAuthor
        UserServiceModel userServiceModel = this.userService.findCurrentUserById();
        entityToSave.setAuthor(this.modelMapper.map(userServiceModel, UserEntity.class));

        //Set GpxCoordinates
        entityToSave.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        RouteEntity savedEntity = this.routeRepository.save(entityToSave);

        return this.modelMapper.map(savedEntity, RouteServiceModel.class);
    }

    @Override
    public RouteServiceModel checkIfNameExists(String name) {
        return this.routeRepository.findByName(name)
                .map(routeEntity -> this.modelMapper.map(routeEntity, RouteServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<RouteServiceModel> findAllByCategory(CategoryEnum name) {
        CategoryEntity category = this.categoryService.findCategoryByName(name);

        List<RouteEntity> allByCategory = this.routeRepository.findAllByCategory(category);

        return allByCategory
                .stream()
                .map(r -> this.modelMapper.map(r, RouteServiceModel.class))
                .collect(Collectors.toList());
    }
}
