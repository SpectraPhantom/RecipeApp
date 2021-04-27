package spring.recipeapp.services;

import org.springframework.stereotype.Service;
import spring.recipeapp.commands.UnitOfMeasureCommand;
import spring.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import spring.recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    UnitOfMeasureRepository unitOfMeasureRepository;
    UnitOfMeasureToUnitOfMeasureCommand myConverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand myConverter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.myConverter = myConverter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listOfUom() {

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(),false)
                .map(myConverter::convert)
                .collect(Collectors.toSet());

    }
}
