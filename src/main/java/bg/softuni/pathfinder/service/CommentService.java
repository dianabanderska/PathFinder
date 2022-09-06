package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.service.CommentServiceModel;
import bg.softuni.pathfinder.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    CommentViewModel createComment(CommentServiceModel commentServiceModel);

    List<CommentViewModel> getComments(Long routeId);
}
