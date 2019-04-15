package com.example.recipe.services;

import com.example.recipe.domain.Recipe;
import com.example.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    private ImageService imageService;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void testSaveImageFile() throws IOException {
        //given
        long recipeId = 1L;
        MultipartFile mockMultipartFile = new MockMultipartFile(
                "imageFile",
                "test.text",
                "text/plain",
                "This is a sample content".getBytes());

        Recipe sampleRecipe = new Recipe();
        sampleRecipe.setId(recipeId);

        Optional<Recipe> expectedRecipe = Optional.of(sampleRecipe);

        when(recipeRepository.findById(recipeId)).thenReturn(expectedRecipe);

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        //when
        imageService.saveImageFile(recipeId, mockMultipartFile);

        //then
        verify(recipeRepository, times(1)).save(recipeArgumentCaptor.capture());
        Recipe savedRecipe = recipeArgumentCaptor.getValue();
        assertEquals(mockMultipartFile.getBytes().length, savedRecipe.getImage().length);


    }
}