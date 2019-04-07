package com.example.recipe.services;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findRecipeById(Long id);

    RecipeCommand findRecipeCommandById(Long id);

    RecipeCommand saveCommand(RecipeCommand recipeCommand);

    void deleteById(Long id);
}
