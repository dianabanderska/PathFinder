package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entities.CategoryEntity;
import bg.softuni.pathfinder.model.entities.RouteEntity;
import bg.softuni.pathfinder.model.service.RouteServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    Optional<RouteEntity> findByName(String name);

    @Query("SELECT DISTINCT(r) FROM RouteEntity AS r JOIN r.categories AS c WHERE c = :category")
    List<RouteEntity> findAllByCategory(CategoryEntity category);
}
