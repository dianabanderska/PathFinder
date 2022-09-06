package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.service.UserServiceModel;
import bg.softuni.pathfinder.model.view.UserProfileViewModel;

public interface UserService {
    boolean checkIfUsernameIsAvailable(String username);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUser(UserServiceModel userServiceModel);

    UserServiceModel findUserById(Long id);

    void loginUser(UserServiceModel userServiceModel);

    void logOut();

    boolean isLoggedIn();

    UserServiceModel findCurrentUserById();
}
