package spring.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

    Optional<UnitOfMeasure> findUnitOfMeasureByDescription(String description);
}
