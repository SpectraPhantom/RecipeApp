package spring.recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.recipeapp.commands.UnitOfMeasureCommand;
import spring.recipeapp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Override
    @Nullable
    @Synchronized
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
       if(source == null){
           return null;
       }
       final UnitOfMeasureCommand unitOfMeasureCommand=new UnitOfMeasureCommand();
       unitOfMeasureCommand.setId(source.getId());
       unitOfMeasureCommand.setDescription(source.getDescription());
       return unitOfMeasureCommand;
    }
}
