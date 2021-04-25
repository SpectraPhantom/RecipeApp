package spring.recipeapp.services;

import org.springframework.stereotype.Service;
import spring.recipeapp.domain.Recipe;
import spring.recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
            throw new RuntimeException("Recipe not found!");
        }
        return recipeOptional.get();
    }
}
