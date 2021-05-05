package spring.recipeapp.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.thymeleaf.exceptions.TemplateInputException;
import spring.recipeapp.exceptions.NotFoundException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public String handleWebExchangeBindException(Model model,Exception exception){
        log.error("Handling web exchange bind exception!");
        log.debug(exception.getMessage());
        model.addAttribute("exception",exception);
        return "400error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
    public String handleNotFound(Model model,Exception exception){
        log.error("Handling not found exception!");
        log.debug(exception.getMessage());
        model.addAttribute("exception",exception);
        return  "404error";
    }
}
