package spring.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.recipeapp.commands.RecipeCommand;
import spring.recipeapp.converters.RecipeCommandToRecipe;
import spring.recipeapp.converters.RecipeToRecipeCommand;
import spring.recipeapp.domain.Recipe;
import spring.recipeapp.exceptions.NotFoundException;
import spring.recipeapp.repositories.RecipeRepository;
import spring.recipeapp.repositories.reactive.RecipeReactiveRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepository recipeReactiveRepository;
    private final RecipeCommandToRecipe recipeConverter;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeConverter, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeReactiveRepository=recipeReactiveRepository;
        this.recipeConverter = recipeConverter;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getRecipes() {
       return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        Recipe recipe=recipeReactiveRepository.findById(id).block();
        if(recipe==null){
            throw new RuntimeException("Recipe not found!");
        }
        return Mono.just(recipe);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {
        return recipeReactiveRepository.findById(id)
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        return recipeReactiveRepository.save(recipeConverter.convert(command))
                .map(recipeToRecipeCommand::convert);

    }

    @Override
    public Mono<Void> deleteById(String id) {
        recipeReactiveRepository.deleteById(id);
        return Mono.empty();
    }
}
