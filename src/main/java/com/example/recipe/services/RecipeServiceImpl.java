package com.example.recipe.services;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.converters.RecipeCommandToRecipe;
import com.example.recipe.converters.RecipeToRecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Recipe findRecipeById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException();
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findRecipeCommandById(Long id) {
        return recipeToRecipeCommand.convert(findRecipeById(id));
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
    @Transactional
    public RecipeCommand saveCommand(RecipeCommand recipeCommand) {

        Recipe savedRecipe = recipeRepository.save(recipeCommandToRecipe.convert(recipeCommand));

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("Recipe deleted: " + id);
        recipeRepository.deleteById(id);
    }
}
