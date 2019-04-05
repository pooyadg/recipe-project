package com.example.recipe.services;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.converters.RecipeCommandToRecipe;
import com.example.recipe.converters.RecipeToRecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    private RecipeCommandToRecipe recipeCommandToRecipe;

    private RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException();
        }

        return recipeOptional.get();
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("In get recipes method.");
        Set<Recipe> recipes = new HashSet<>();
//        recipeRepository.findAll().forEach(recipe -> recipes.add(recipe));
//        recipeRepository.findAll().forEach(recipes::add);
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public RecipeCommand saveCommand(RecipeCommand recipeCommand) {

        Recipe savedRecipe = recipeRepository.save(recipeCommandToRecipe.convert(recipeCommand));

        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
