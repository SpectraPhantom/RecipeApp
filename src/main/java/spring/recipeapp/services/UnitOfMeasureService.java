package spring.recipeapp.services;

import reactor.core.publisher.Flux;
import spring.recipeapp.commands.UnitOfMeasureCommand;


public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> listOfUom();
}
