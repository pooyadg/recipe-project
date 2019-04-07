package com.example.recipe.services;

import com.example.recipe.domain.Recipe;
import com.example.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by pooya on 3/28/2019.
 */
public class RecipeServiceImplTest {

    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, null, null);
    }

    @Test
    public void testGetRecipeById() throws Exception {

        Long sampleId = 1L;

        Recipe sampleRecipe = new Recipe();
        sampleRecipe.setId(sampleId);
        Optional<Recipe> recipeOptional = Optional.of(sampleRecipe);
        Mockito.when(recipeRepository.findById(sampleId)).thenReturn(recipeOptional);

        Recipe recipe = recipeService.findRecipeById(sampleId);

        assertNotNull(recipe);
        assertEquals(sampleId, recipe.getId());

    }

    @Test
    public void getRecipes() throws Exception {
        Recipe recipe = new Recipe();
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);

        Mockito.when(recipeService.getRecipes()).thenReturn(recipeSet);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(1, recipes.size());

        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void testDeleteRecipeById() {

        //given
        Long sampleId = 1L;

        //when
        recipeService.deleteById(sampleId);

        //then
        Mockito.verify(recipeRepository, Mockito.times(1)).deleteById(Mockito.anyLong());

    }
}