package spring.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.recipeapp.commands.NotesCommand;
import spring.recipeapp.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Override
    public Notes convert(NotesCommand source) {
        if(source == null){
            return null;
        }
        final Notes notes=new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
