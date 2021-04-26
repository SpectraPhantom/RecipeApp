package spring.recipeapp.services;

import org.springframework.stereotype.Service;
import spring.recipeapp.commands.IngredientCommand;
import spring.recipeapp.converters.IngredientToIngredientCommand;
import spring.recipeapp.domain.Recipe;
import spring.recipeapp.repositories.RecipeRepository;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;


    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional=recipeRepository.findById(recipeId);
        if(recipeOptional.isEmpty()){
            throw new RuntimeException("Recipe Not Found");
        }

        Recipe recipe=recipeOptional.get();

        Optional<IngredientCommand> ingredientCommand=recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();


        return ingredientCommand.get();
    }
}
