package spring.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe,String> {
}
