package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entities.CategoryEntity;
import bg.softuni.pathfinder.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryEnum name);
}
