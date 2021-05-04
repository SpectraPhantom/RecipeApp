package spring.recipeapp.services;

import spring.recipeapp.commands.RecipeCommand;
import spring.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(String id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(String id);
    void deleteById(String id);
}
