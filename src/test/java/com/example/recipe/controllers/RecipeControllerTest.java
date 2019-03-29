package com.example.recipe.controllers;

import com.example.recipe.domain.Recipe;
import com.example.recipe.repositories.RecipeRepository;
import com.example.recipe.services.RecipeService;
import com.example.recipe.services.RecipeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Optional;

/**
 * Created by pooya on 3/29/2019.
 */
public class RecipeControllerTest {


    private RecipeDetailsController recipeDetailsController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeDetailsController = new RecipeDetailsController(recipeService);
    }

    @Test
    public void testGetRecipeDetailsById() throws Exception {

        Long sampleId = 1L;

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeDetailsController).build();

        String detailsView = recipeDetailsController.getRecipeDetailsById(sampleId.toString(), model);

        String expectedViewName = "/recipe/details";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/recipe/details/" + sampleId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(expectedViewName))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));


        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipe"), Mockito.any());
        Assert.assertEquals(expectedViewName, detailsView);

    }

}