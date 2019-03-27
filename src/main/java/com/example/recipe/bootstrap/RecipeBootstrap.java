package com.example.recipe.bootstrap;


import com.example.recipe.domain.*;
import com.example.recipe.repositories.CategoryRepository;
import com.example.recipe.repositories.RecipeRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }


    private Set<Recipe> getRecipes() {

        Set<Recipe> recipes = new HashSet<>();

        Recipe firstRecipe = new Recipe();
        firstRecipe.setCookTime(20000);
        firstRecipe.setDescription("This is a simple description for the first recipe.");
        firstRecipe.setDifficulty(Difficulty.MODERATE);
        firstRecipe.setDirections("Left to right");

        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(BigDecimal.valueOf(2000000));
        ingredient.setDescription("Salt");
        ingredient.setRecipe(firstRecipe);
//        ingredients.add(ingredient);
        firstRecipe.addIngredients(ingredient);


        ingredient = new Ingredient();
        ingredient.setAmount(BigDecimal.valueOf(34780000));
        ingredient.setDescription("Peper");
        ingredient.setRecipe(firstRecipe);
//        ingredients.add(ingredient);
        firstRecipe.addIngredients(ingredient);

//        firstRecipe.setIngredients(ingredients);

        Notes notes = new Notes();
        notes.setNotes("This is a simple note for the first recipe.");
        notes.setRecipe(firstRecipe);

        firstRecipe.setNotes(notes);

        Iterable<Category> categoryIterable = categoryRepository.findAll();
        Set<Category> categories = new HashSet<>();
        Iterator<Category> categoryIterator = categoryIterable.iterator();
        Category category = categoryIterator.next();
        category.setRecipes(recipes);
        categories.add(category);
        Category category1 = categoryIterator.next();
        category1.setRecipes(recipes);
        categories.add(category1);

        firstRecipe.setCategories(categories);

        recipes.add(firstRecipe);

        return recipes;
    }

}
