package spring.recipeapp.services;

import reactor.core.publisher.Mono;
import spring.recipeapp.commands.IngredientCommand;

public interface IngredientService {
    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand ingredientCommand);
    Mono<Void> deleteById(String recipeId, String ingredientId);
}
