package spring.recipeapp.services;

import spring.recipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId,String ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteById(String recipeId,String ingredientId);
}
