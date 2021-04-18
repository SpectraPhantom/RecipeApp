package spring.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
}
