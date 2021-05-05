package spring.recipeapp.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import spring.recipeapp.domain.UnitOfMeasure;


public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure,String> {

   Mono<UnitOfMeasure> findUnitOfMeasureByDescription(String description);
}
