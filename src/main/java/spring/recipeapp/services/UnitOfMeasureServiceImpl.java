package spring.recipeapp.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import spring.recipeapp.commands.UnitOfMeasureCommand;
import spring.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import spring.recipeapp.repositories.UnitOfMeasureRepository;
import spring.recipeapp.repositories.reactive.UnitOfMeasureReactiveRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    UnitOfMeasureToUnitOfMeasureCommand myConverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand myConverter) {
        this.unitOfMeasureReactiveRepository=unitOfMeasureReactiveRepository;
        this.myConverter = myConverter;
    }

    @Override
    public Flux<UnitOfMeasureCommand> listOfUom() {
        return unitOfMeasureReactiveRepository.findAll()
                .map(myConverter::convert);
    }
}
