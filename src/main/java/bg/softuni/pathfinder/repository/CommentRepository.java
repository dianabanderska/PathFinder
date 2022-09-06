package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
