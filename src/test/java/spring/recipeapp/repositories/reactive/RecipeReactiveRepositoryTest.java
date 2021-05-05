package spring.recipeapp.repositories.reactive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import spring.recipeapp.domain.Recipe;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @BeforeEach
    void setUp() {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    void testRecipeSave() {
        Recipe recipe=new Recipe();
        recipe.setDescription("test");

        recipeReactiveRepository.save(recipe).block();

        Long counter=recipeReactiveRepository.count().block();

        assertEquals(counter,recipeReactiveRepository.count().block());
    }
}