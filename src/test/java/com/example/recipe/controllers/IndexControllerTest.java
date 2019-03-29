package com.example.recipe.controllers;

import com.example.recipe.domain.Category;
import com.example.recipe.domain.Recipe;
import com.example.recipe.domain.UnitOfMeasure;
import com.example.recipe.repositories.CategoryRepository;
import com.example.recipe.repositories.UnitOfMeasureRepository;
import com.example.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Created by pooya on 3/29/2019.
 */
public class IndexControllerTest {


    private IndexController indexController;

    @Mock
    private Model model;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(unitOfMeasureRepository, categoryRepository, recipeService);
    }

    @Test
    public void testMockMvc() throws Exception {

        Category category = new Category();
        category.setId(22L);
        Optional<Category> optionalCategory = Optional.of(category);
        Mockito.when(categoryRepository.findByName("Mexican")).thenReturn(optionalCategory);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(44L);
        Optional<UnitOfMeasure> optionalUnit = Optional.of(unitOfMeasure);
        Mockito.when(unitOfMeasureRepository.findByUnit("Tablespoon")).thenReturn(optionalUnit);


        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));


    }

    @Test
    public void getIndex() throws Exception {


        //given
        Category category = new Category();
        category.setId(22L);
        Optional<Category> optionalCategory = Optional.of(category);
        Mockito.when(categoryRepository.findByName("Mexican")).thenReturn(optionalCategory);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(44L);
        Optional<UnitOfMeasure> optionalUnit = Optional.of(unitOfMeasure);
        Mockito.when(unitOfMeasureRepository.findByUnit("Tablespoon")).thenReturn(optionalUnit);


        Set<Recipe> recipes = new HashSet<>(2);
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set> setArgumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndex(model);

        //then
        assertEquals("index", viewName);
        Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
//        Mockito.verify(model, Mockito.times(1)).addAttribute(eq("recipes"), Mockito.anySet());
        Mockito.verify(model, Mockito.times(1)).addAttribute(eq("recipes"), setArgumentCaptor.capture());
        assertEquals(2, setArgumentCaptor.getValue().size());


    }

}