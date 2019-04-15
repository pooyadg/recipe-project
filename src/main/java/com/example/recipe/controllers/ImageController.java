package com.example.recipe.controllers;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.domain.Recipe;
import com.example.recipe.services.ImageService;
import com.example.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private ImageService imageService;
    private RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {

        model.addAttribute("recipeId", id);

        return "/recipe/imageform";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imageFile") MultipartFile file) {

        log.debug("In get new image");

        imageService.saveImageFile(Long.parseLong(id), file);

        return "redirect:/recipe/" + id + "/details";
    }

    @GetMapping("recipe/{id}/recipe-image")
    public void renderImageFromDb(@PathVariable String id, HttpServletResponse response) throws IOException {

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.parseLong(id));

        Byte[] imageBytes = recipeCommand.getImage();
        byte[] bytes = new byte[imageBytes.length];
        for (int i = 0; i < imageBytes.length; i++) {
            bytes[i] = imageBytes[i];
        }
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());


    }


}
