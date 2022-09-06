package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.binding.UserLoginBindingModel;
import bg.softuni.pathfinder.model.binding.UserRegisterBindingModel;
import bg.softuni.pathfinder.model.enums.LevelEnum;
import bg.softuni.pathfinder.model.service.UserServiceModel;
import bg.softuni.pathfinder.model.view.UserProfileViewModel;
import bg.softuni.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public String getLogin(Model model) {

        if (!model.containsAttribute("isUserValid")) {
            model.addAttribute("isUserValid", true);
        }

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userLoginBindingModel, UserServiceModel.class);

        UserServiceModel existingUser = this.userService.findUser(userServiceModel);

        if (existingUser == null) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("isUserValid", false);

            return "redirect:login";
        }

        this.userService.loginUser(existingUser);

        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        if (!model.containsAttribute("isMatch")) {
            model.addAttribute("isMatch", true);
        }
        if (!model.containsAttribute("isUsernameAvailable")) {
            model.addAttribute("isUsernameAvailable", true);
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                redirectAttributes.addFlashAttribute("isMatch", false);
            }

            return "redirect:register";
        }

        boolean isUsernameAvailable = this.userService.checkIfUsernameIsAvailable(userRegisterBindingModel.getUsername());

        if (!isUsernameAvailable) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("isUsernameAvailable", false);

            return "redirect:register";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        userServiceModel.setLevel(LevelEnum.BEGINNER);
        this.userService.registerUser(userServiceModel);

        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logOut();

        return "index";
    }

    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {

        UserServiceModel userServiceModel = this.userService.findUserById(id);
        model.addAttribute("profileView", this.modelMapper.map(userServiceModel, UserProfileViewModel.class));

        return "profile";
    }
}
