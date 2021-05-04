package spring.recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.recipeapp.commands.RecipeCommand;
import spring.recipeapp.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, UnitOfMeasureCommandToUnitOfMeasure uomConverter,
                                 CategoryCommandToCategory categoryConverter,
                                 IngredientCommandToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.uomConverter = uomConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public Recipe convert(RecipeCommand source) {
        if(source == null){
            return null;
        }
        final Recipe recipe=new Recipe();
        recipe.setId(source.getId());
        recipe.setNotes(notesConverter.convert(source.getNotes()));
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDescription(source.getDescription());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setServings(source.getServings());
        recipe.setImage(source.getImage());

        if(source.getCategories()!=null && source.getCategories().size()>0){
            source.getCategories().forEach(category->recipe.getCategories().add(category));
        }

        if(source.getIngredients()!=null && source.getIngredients().size()>0){
            source.getIngredients().forEach(ingredient->recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
