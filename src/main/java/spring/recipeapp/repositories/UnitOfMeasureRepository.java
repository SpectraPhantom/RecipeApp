package spring.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,String> {

    Optional<UnitOfMeasure> findUnitOfMeasureByDescription(String description);
}
