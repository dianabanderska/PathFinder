package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.binding.RouteAddBindingModel;
import bg.softuni.pathfinder.model.enums.CategoryEnum;
import bg.softuni.pathfinder.model.service.RouteServiceModel;

import java.io.IOException;
import java.util.List;

public interface RouteService {
    List<RouteServiceModel> getAllRoutes();

    RouteServiceModel findById(Long id);

    RouteServiceModel saveRoute(RouteAddBindingModel routeAddBindingModel) throws IOException;

    RouteServiceModel checkIfNameExists(String name);

    List<RouteServiceModel> findAllByCategory(CategoryEnum name);
}
