package spring.recipeapp.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import spring.recipeapp.domain.Recipe;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe,String> {
}
