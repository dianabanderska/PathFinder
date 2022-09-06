package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.entities.UserEntity;
import bg.softuni.pathfinder.model.service.UserServiceModel;
import bg.softuni.pathfinder.model.view.UserProfileViewModel;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.security.CurrentUser;
import bg.softuni.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean checkIfUsernameIsAvailable(String username) {
        return this.userRepo.findByUsername(username).isEmpty();
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity userEntity = this.modelMapper.map(userServiceModel, UserEntity.class);
        this.userRepo.save(userEntity);
    }

    @Override
    public UserServiceModel findUser(UserServiceModel userServiceModel) {
        return this.userRepo.findByUsernameAndPassword(userServiceModel.getUsername(), userServiceModel.getPassword())
                .map(userEntity -> this.modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel findUserById(Long id) {
       return this.userRepo.findById(id)
               .map(userEntity -> this.modelMapper.map(userEntity, UserServiceModel.class))
               .orElse(null);
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {
        this.currentUser.setId(userServiceModel.getId());
        this.currentUser.setUsername(userServiceModel.getUsername());
    }

    @Override
    public void logOut() {
        this.currentUser.setId(null);
        this.currentUser.setUsername(null);
    }

    @Override
    public boolean isLoggedIn() {
        if(this.currentUser.getId() == null) {
            return false;
        }
        return true;
    }

    @Override
    public UserServiceModel findCurrentUserById() {
        return this.userRepo.findById(this.currentUser.getId())
                .map(userEntity -> this.modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);
    }
}
