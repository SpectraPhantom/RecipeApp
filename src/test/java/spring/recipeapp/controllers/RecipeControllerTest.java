package spring.recipeapp.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.recipeapp.commands.RecipeCommand;
import spring.recipeapp.domain.Recipe;
import spring.recipeapp.exceptions.NotFoundException;
import spring.recipeapp.services.RecipeService;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc=MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void showById() throws Exception{
     Recipe recipe=new Recipe();
     recipe.setId(1L);
     when(recipeService.findById(anyLong())).thenReturn(recipe);

     mockMvc.perform(get("/recipe/1/show"))
             .andExpect(status().isOk())
             .andExpect(view().name("recipe/show"));
    }

    @Test
    void newRecipeTest() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void getUpdateView() throws Exception {
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void saveOrUpdatePostMappingTest() throws Exception {
        RecipeCommand command=new RecipeCommand();
        command.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")
                .param("description","some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    void deleteRecipeByIdTest() throws Exception{

        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));

    }

    @Test
    void findRecipeByIdNotFound() throws Exception {
      when((recipeService.findById(anyLong()))).thenThrow(NotFoundException.class);
      mockMvc.perform(get("/recipe/1/show"))
              .andExpect(status().isNotFound())
              .andExpect(view().name("404error"));
    }

    @Test
    void findRecipeByIdNumberFormatException() throws Exception{

        mockMvc.perform(get("/recipe/ssada/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}