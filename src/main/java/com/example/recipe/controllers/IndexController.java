package com.example.recipe.controllers;

import com.example.recipe.repositories.CategoryRepository;
import com.example.recipe.repositories.UnitOfMeasureRepository;
import com.example.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pooya on 3/18/2019.
 */

@Slf4j
@Controller
public class IndexController {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    private RecipeService recipeService;


    public IndexController(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeService recipeService) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndex(Model model) {

        log.debug("getIndex method just called....");

        System.out.println("Category : " + categoryRepository.findByName("Mexican").get().getId());
        System.out.println("Unit of measure " + unitOfMeasureRepository.findByUnit("Tablespoon").get().getId());

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
