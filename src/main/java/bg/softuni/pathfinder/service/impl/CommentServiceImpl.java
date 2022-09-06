package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.exceptions.ObjectNotFoundException;
import bg.softuni.pathfinder.model.entities.CommentEntity;
import bg.softuni.pathfinder.model.service.CommentServiceModel;
import bg.softuni.pathfinder.model.view.CommentViewModel;
import bg.softuni.pathfinder.repository.CommentRepository;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.CommentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(RouteRepository routeRepository,
                              UserRepository userRepository,
                              CommentRepository commentRepository) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {
        Objects.requireNonNull(commentServiceModel.getCreator());

        var route = routeRepository.
                findById(commentServiceModel.getRouteId()).
                orElseThrow(() -> new ObjectNotFoundException("Route with id " + commentServiceModel.getRouteId() + " not found!"));

        var author = userRepository.
                findByUsername(commentServiceModel.getCreator()).
                orElseThrow(() -> new ObjectNotFoundException("User with eamil " + commentServiceModel.getCreator() + " not found!"));

        CommentEntity newComment = new CommentEntity();
        newComment.setApproved(false);
        newComment.setTextContent(commentServiceModel.getMessage());
        newComment.setCreated(LocalDateTime.now());
        newComment.setRoute(route);
        newComment.setAuthor(author);

        CommentEntity savedComment = commentRepository.save(newComment);

        return mapAsComment(savedComment);
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long routeId) {
        var routeOpt = routeRepository.
                findById(routeId);

        if (routeOpt.isEmpty()) {
            throw new ObjectNotFoundException("Route with id " + routeId + " was not found!");
        }

        return routeOpt.
                get().
                getComments().
                stream().
                map(this::mapAsComment).
                collect(Collectors.toList());
    }

    private CommentViewModel mapAsComment(CommentEntity commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.
                setCommentId(commentEntity.getId()).
                setCanApprove(true).
                setCanDelete(true).
                setCreated(commentEntity.getCreated()).
                setMessage(commentEntity.getTextContent()).
                setUser(commentEntity.getAuthor().getFullName());


        return commentViewModel;
    }
}
