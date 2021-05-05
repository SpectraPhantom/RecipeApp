package spring.recipeapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;
import spring.recipeapp.domain.Recipe;
import spring.recipeapp.services.RecipeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

class IndexControllerTest {


    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController controller;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller=new IndexController(recipeService);

    }

    @Test
    void testMockMvc() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(controller).build();

        when(recipeService.getRecipes()).thenReturn(Flux.empty());
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }


    @Test
    void getIndexPage() {

        //given
        Set<Recipe> recipes=new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe=new Recipe();
        recipe.setId("4");

        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(Flux.fromIterable(recipes));

        ArgumentCaptor<List<Recipe>> argumentCaptor=ArgumentCaptor.forClass(List.class);


        //when
        String viewName=controller.getIndexPage(model);

        //then
        assertEquals("index",viewName);
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        List<Recipe> setInController=argumentCaptor.getValue();
        assertEquals(2,setInController.size());


    }
}