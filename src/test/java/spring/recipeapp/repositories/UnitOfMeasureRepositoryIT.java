package spring.recipeapp.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import spring.recipeapp.bootstrap.RecipeBootstrap;
import spring.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
        recipeRepository.deleteAll();

        RecipeBootstrap recipeBootstrap=new RecipeBootstrap(recipeRepository,categoryRepository,unitOfMeasureRepository);
        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    void findUnitOfMeasureByDescription() {

        Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");
        assertEquals("Teaspoon",unitOfMeasureOptional.get().getDescription());
    }
}