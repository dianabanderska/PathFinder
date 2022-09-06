package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.binding.RouteAddBindingModel;
import bg.softuni.pathfinder.model.enums.CategoryEnum;
import bg.softuni.pathfinder.model.service.RouteServiceModel;
import bg.softuni.pathfinder.model.view.PictureView;
import bg.softuni.pathfinder.model.view.RouteExtendedView;
import bg.softuni.pathfinder.model.view.RouteMiniViewModel;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public RouteController(RouteService routesService, ModelMapper modelMapper, UserService userService) {
        this.routeService = routesService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllRoutes(Model model) {

        List<RouteServiceModel> allRoutes = this.routeService.getAllRoutes();
        model.addAttribute("routeViews", allRoutes
                .stream()
                .map(r ->{
                    RouteMiniViewModel routeMiniViewModel = this.modelMapper.map(r, RouteMiniViewModel.class);
                    routeMiniViewModel.setPicture(choosePictureViewToSend(r));
                    return routeMiniViewModel;
                })
                .collect(Collectors.toList()));

        return "routes";
    }

    @GetMapping("/details/{id}")
    public String getRouteDetails(@PathVariable Long id, Model model) {
        RouteServiceModel routeServiceModel = this.routeService.findById(id);

        RouteExtendedView routeView = this.modelMapper.map(routeServiceModel, RouteExtendedView.class);
        routeView.setAuthorName(routeServiceModel.getAuthor().getFullName());

        model.addAttribute("routeView", routeView);

        return "route-details";
    }

    @GetMapping("/add")
    public String getAddRoute(Model model) {

        if(!this.userService.isLoggedIn()){
            return"redirect:/users/login";
        }

        model.addAttribute("doesRouteExist", false);

        return "add-route";
    }

    @PostMapping("/add")
    public String postAddRoute(@Valid RouteAddBindingModel routeAddBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) throws IOException {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeAddBindingModel", routeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);

            return "redirect:add";
        }

        RouteServiceModel existingRoute = this.routeService.checkIfNameExists(routeAddBindingModel.getName());
        if(existingRoute != null) {
            redirectAttributes.addFlashAttribute("routeAddBindingModel", routeAddBindingModel);
            redirectAttributes.addFlashAttribute("doesRouteExist", true);

            return "redirect:add";
        }

        RouteServiceModel routeServiceModel = this.routeService.saveRoute(routeAddBindingModel);

        return "redirect:all";
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }

    @GetMapping("/{enum}")
    private String getPedestrianRoutes(Model model, @PathVariable("enum") String category) {
        String var = category.toUpperCase();
        CategoryEnum categoryEnum = CategoryEnum.valueOf(var);
        List<RouteServiceModel> routeServiceModels = this.routeService.findAllByCategory(categoryEnum);

        model.addAttribute("filteredRoutes", routeServiceModels.stream()
                .map(r -> {
                    RouteMiniViewModel routeMiniViewModel = this.modelMapper.map(r, RouteMiniViewModel.class);
                    routeMiniViewModel.setPicture(choosePictureViewToSend(r));
                    return routeMiniViewModel;
                })
                .collect(Collectors.toList()));

        switch(category) {
            case "pedestrian": return "pedestrian";
            case "bicycle": return "bicycle";
            case "motorcycle": return "motorcycle";
            case "car": return "car";
            default: return "index";
        }
    }

    private PictureView choosePictureViewToSend(RouteServiceModel r) {
        return r.getPictures().isEmpty()
                ? new PictureView("/images/pic4.jpg", "Title image")
                : r.getPictures()
                .stream()
                .map(pictureEntity -> this.modelMapper.map(pictureEntity, PictureView.class))
                .toList()
                .get(new Random().nextInt(0, r.getPictures().size() - 1));
    }

}
