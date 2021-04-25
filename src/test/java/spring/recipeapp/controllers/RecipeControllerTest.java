package spring.recipeapp.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.recipeapp.domain.Recipe;
import spring.recipeapp.services.RecipeService;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController controller;


    @Test
    void showById() throws Exception{
     Recipe recipe=new Recipe();
     recipe.setId(1L);
     MockMvc mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
     when(recipeService.findById(anyLong())).thenReturn(recipe);

     mockMvc.perform(get("/recipe/show/1"))
             .andExpect(status().isOk())
             .andExpect(view().name("recipe/show"));
    }
}