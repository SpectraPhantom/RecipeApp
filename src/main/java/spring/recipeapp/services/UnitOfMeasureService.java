package spring.recipeapp.services;

import spring.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listOfUom();
}
