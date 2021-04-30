package spring.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.recipeapp.commands.RecipeCommand;
import spring.recipeapp.converters.RecipeCommandToRecipe;
import spring.recipeapp.converters.RecipeToRecipeCommand;
import spring.recipeapp.domain.Recipe;
import spring.recipeapp.exceptions.NotFoundException;
import spring.recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeConverter;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeConverter, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeConverter = recipeConverter;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes=new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional=recipeRepository.findById(id);
        if (recipeOptional.isEmpty()){
            throw new NotFoundException("Recipe not found! For ID value: "+ id);
        }
        if(id.getClass().equals(String.class)){
            throw new NumberFormatException("Recipe ID should be in number format!");
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
       Recipe detachedRecipe= recipeConverter.convert(command);
       Recipe savedRecipe=recipeRepository.save(detachedRecipe);
       log.debug("Saved recipe ID: "+ savedRecipe.getId());
       return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
        log.debug("Recipe number: "+id+" deleted");
    }
}
