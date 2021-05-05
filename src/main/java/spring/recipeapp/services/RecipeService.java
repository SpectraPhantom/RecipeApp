package spring.recipeapp.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.recipeapp.commands.RecipeCommand;
import spring.recipeapp.domain.Recipe;


public interface RecipeService {

    Flux<Recipe> getRecipes();
    Mono<Recipe> findById(String id);
    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);
    Mono<RecipeCommand> findCommandById(String id);
    Mono<Void> deleteById(String id);
}
