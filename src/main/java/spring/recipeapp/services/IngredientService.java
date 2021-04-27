package spring.recipeapp.services;

import spring.recipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
