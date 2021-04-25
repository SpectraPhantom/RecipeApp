package spring.recipeapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring.recipeapp.converters.RecipeCommandToRecipe;
import spring.recipeapp.converters.RecipeToRecipeCommand;
import spring.recipeapp.domain.Recipe;
import spring.recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;


    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeConverter;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService=new RecipeServiceImpl(recipeRepository, recipeConverter, recipeToRecipeCommand);

    }

    @Test
    void getRecipes() {
        Recipe recipe=new Recipe();
        HashSet recipeData=new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes=recipeService.getRecipes();
        assertEquals(recipes.size(),1);

        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    void getRecipeById() {
        Recipe recipe=new Recipe();
        recipe.setId(1L);

        Optional<Recipe> recipeOptional=Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned=recipeService.findById(1L);

        assertNotNull(recipeReturned);

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();

    }
}