package com.example.recipe.controllers;

import com.example.recipe.repositories.CategoryRepository;
import com.example.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pooya on 3/18/2019.
 */

@Controller
public class IndexController {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    public IndexController(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndex() {

        System.out.println("Category : " + categoryRepository.findByName("Mexican").get().getId());
        System.out.println("Unit of measure " + unitOfMeasureRepository.findByUnit("Tablespoon").get().getId());

        return "index";
    }

}
