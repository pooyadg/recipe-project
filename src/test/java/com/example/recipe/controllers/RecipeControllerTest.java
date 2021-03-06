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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    public static final Long SAMPLE_ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeDetailsController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeDetailsController).build();
    }

    @Test
    public void testGetRecipeDetailsById() throws Exception {

        //given
        Long SAMPLE_ID = 1L;
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(SAMPLE_ID);

        Mockito.when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        String detailsView = recipeDetailsController.getRecipeDetailsById(SAMPLE_ID.toString(), model);


        //then
        String expectedViewName = "/recipe/details";
        mockMvc.perform(get("/recipe/" + SAMPLE_ID.toString() + "/details/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(expectedViewName))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));


        verify(model, times(1)).addAttribute(Mockito.eq("recipe"), Mockito.any());
        Assert.assertEquals(expectedViewName, detailsView);

    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        //when then
        mockMvc.perform(get("/recipe/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipe/recipeform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    public void testPostRecipeForm() throws Exception {

        //given
        Long SAMPLE_ID = 1L;
        RecipeCommand expectedReturn = new RecipeCommand();
        expectedReturn.setId(SAMPLE_ID);

        Mockito.when(recipeService.saveCommand(Mockito.any())).thenReturn(expectedReturn);

        //when then
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "Some strings"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/" + SAMPLE_ID.toString() + "/details"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(SAMPLE_ID);

        RecipeCommand expectedCommand = new RecipeCommand();
        expectedCommand.setId(SAMPLE_ID);

        Mockito.when(recipeService.findCommandById(Mockito.any())).thenReturn(expectedCommand);

        //when then
        mockMvc.perform(get("/recipe/" + SAMPLE_ID.toString() + "/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipe/recipeform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

    }

    @Test
    public void testDeleteRecipe() throws Exception {
        //given

        //when //then
        mockMvc.perform(get("/recipe/" + SAMPLE_ID + "/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());

    }
}