package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.binding.NewCommentBindingModel;
import bg.softuni.pathfinder.model.service.CommentServiceModel;
import bg.softuni.pathfinder.model.view.CommentViewModel;
import bg.softuni.pathfinder.service.CommentService;
import bg.softuni.pathfinder.validation.ApiError;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/{routeId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(
            @PathVariable Long routeId,
            Principal principal
    ) {
        return ResponseEntity.ok(
                commentService.getComments(routeId));
    }

    @PostMapping("/api/{routeId}/comments")
    public ResponseEntity<CommentViewModel> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long routeId,
            @RequestBody @Valid NewCommentBindingModel newCommentBindingModel
    ) {
        CommentServiceModel serviceModel =
                modelMapper.map(newCommentBindingModel, CommentServiceModel.class);
        serviceModel.setCreator(principal.getUsername());
        serviceModel.setRouteId(routeId);

        CommentViewModel newComment =
                commentService.createComment(serviceModel);

        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s", routeId, newComment.getCommentId()));

        return ResponseEntity.
                created(locationOfNewComment).
                body(newComment);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }
}
