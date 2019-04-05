package com.example.recipe.controllers;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

/**
 * Created by pooya on 3/29/2019.
 */
public class RecipeControllerTest {


    private RecipeController recipeDetailsController;

    private MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeDetailsController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeDetailsController).build();
    }

    @Test
    public void testGetRecipeDetailsById() throws Exception {

        //given
        Long sampleId = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(sampleId);

        Mockito.when(recipeService.findRecipeById(Mockito.anyLong())).thenReturn(recipe);

        //when
        String detailsView = recipeDetailsController.getRecipeDetailsById(sampleId.toString(), model);


        //then
        String expectedViewName = "/recipe/details";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/recipe/" + sampleId.toString() + "/details/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(expectedViewName))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));


        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipe"), Mockito.any());
        Assert.assertEquals(expectedViewName, detailsView);

    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        //when then
        mockMvc.perform(MockMvcRequestBuilders.
                get("/recipe/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipe/recipeform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    public void testPostRecipeForm() throws Exception {

        //given
        Long sampleId = 1L;
        RecipeCommand expectedReturn = new RecipeCommand();
        expectedReturn.setId(sampleId);

        Mockito.when(recipeService.saveCommand(Mockito.any())).thenReturn(expectedReturn);

        //when then
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "Some strings"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/" + sampleId.toString() + "/details"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        //given
        Long sampleId = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(sampleId);

        RecipeCommand expectedCommand = new RecipeCommand();
        expectedCommand.setId(sampleId);

        Mockito.when(recipeService.findRecipeCommandById(Mockito.any())).thenReturn(expectedCommand);

        //when then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + sampleId.toString() + "/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipe/recipeform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

    }
}