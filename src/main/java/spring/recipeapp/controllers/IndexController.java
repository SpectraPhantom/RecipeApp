package spring.recipeapp.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.recipeapp.services.RecipeService;


@Controller
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"","/","index","index.html"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes",recipeService.getRecipes().collectList().block());
        log.debug("I`m index controller");
        return "index";
    }


}
