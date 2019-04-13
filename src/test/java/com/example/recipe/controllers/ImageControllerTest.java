package com.example.recipe.controllers;

import com.example.recipe.services.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ImageControllerTest {

    private ImageController imageController;

    @Mock
    private Model model;

    @Mock
    private ImageService imageService;

    private MockMvc mockMvc;
    public static final Long SAMPLE_ID = 1L;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void testGetImageView() throws Exception {
        //given

        //when then
        mockMvc.perform(get("/recipe/" + SAMPLE_ID + "/image"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipeId"))
                .andExpect(MockMvcResultMatchers.view().name("/recipe/imageform"));

    }

    @Test
    public void testHandleImagePost() throws Exception {
        //given
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "imageFile",
                "test.text",
                "text/plain",
                "This is a sample content".getBytes());

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/recipe/" + SAMPLE_ID + "/image")
                .file(mockMultipartFile))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/recipe/" + SAMPLE_ID + "/details"))
        ;

        Mockito.verify(imageService, Mockito.times(1)).saveImageFile(anyLong(), any());


    }
}
