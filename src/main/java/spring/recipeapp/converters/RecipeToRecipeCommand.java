package spring.recipeapp.converters;


import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.recipeapp.commands.RecipeCommand;
import spring.recipeapp.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter,
                                 IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    @Synchronized
    @Nullable
    public RecipeCommand convert(Recipe source) {
        if(source == null){
            return null;
        }
        final RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setId(source.getId());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setImage(source.getImage());

        if(source.getCategories()!=null && source.getCategories().size()>0){
            source.getCategories().forEach(category -> recipeCommand.getCategories()
                    .add(category));
        }

        if(source.getIngredients()!=null && source.getIngredients().size()>0){
            source.getIngredients().forEach(ingredient -> recipeCommand.getIngredients()
                    .add(ingredientConverter.convert(ingredient)));
        }

        return recipeCommand;
    }
}
