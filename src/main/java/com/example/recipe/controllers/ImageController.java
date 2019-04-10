package com.example.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class ImageController {


    @GetMapping("recipe/{id}/image")
    public String getImageView(@PathVariable String id, Model model) {

        model.addAttribute("recipeId", id);

        return "/recipe/imageform";
    }

}
