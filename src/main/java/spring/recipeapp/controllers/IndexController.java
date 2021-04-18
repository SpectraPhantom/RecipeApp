package spring.recipeapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.recipeapp.domain.Category;
import spring.recipeapp.domain.UnitOfMeasure;
import spring.recipeapp.repositories.CategoryRepository;
import spring.recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","index","index.html"})
    public String getIndexPage(){
        Optional<Category> categoryOptional=categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findUnitOfMeasureByDescription("Cup");
        System.out.println("Cat ID is: "+categoryOptional.get().getId());
        System.out.println("UOM ID is: "+unitOfMeasureOptional.get().getId());
        return "index";
    }
}
