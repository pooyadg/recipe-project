package com.example.recipe.services;

import com.example.recipe.domain.Recipe;
import com.example.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipes = new HashSet<>();
//        recipeRepository.findAll().forEach(recipe -> recipes.add(recipe));
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }
}
