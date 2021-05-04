package spring.recipeapp.repositories;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import spring.recipeapp.domain.Recipe;

@EnableMongoRepositories
public interface RecipeRepository extends CrudRepository<Recipe,String> {
}
