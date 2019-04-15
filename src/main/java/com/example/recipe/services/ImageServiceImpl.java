package com.example.recipe.services;

import com.example.recipe.domain.Recipe;
import com.example.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {

        log.debug("Saving the image file for recipient id equal to " + recipeId);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe does not exist by this is" + recipeId);
        }

        try {
            Byte[] bytes = new Byte[file.getBytes().length];
            for (int i = 0; i < file.getBytes().length; i++) {
                bytes[i] = file.getBytes()[i];
            }


            Recipe recipe = recipeOptional.get();
            recipe.setImage(bytes);
            recipeRepository.save(recipe);

            log.debug("Image saved.");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
