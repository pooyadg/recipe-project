package com.example.recipe.controllers;

import com.example.recipe.domain.Recipe;
import com.example.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by pooya on 3/29/2019.
 */
@Controller
public class RecipeDetailsController {

    private RecipeService recipeService;

    public RecipeDetailsController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/details/{id}")
    public String getRecipeDetailsById(@PathVariable String id, Model model) {

        Recipe recipeById = recipeService.getRecipeById(Long.parseLong(id));

        model.addAttribute("recipe", recipeById);

        return "/recipe/details";
    }

}
