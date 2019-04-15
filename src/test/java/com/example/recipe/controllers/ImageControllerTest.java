package com.example.recipe.controllers;

import com.example.recipe.commands.RecipeCommand;
import com.example.recipe.services.ImageService;
import com.example.recipe.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ImageControllerTest {

    private ImageController imageController;

    @Mock
    private Model model;

    @Mock
    private ImageService imageService;

    @Mock
    private RecipeService recipeService;

    private MockMvc mockMvc;
    public static final Long SAMPLE_ID = 1L;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void testGetImageView() throws Exception {
        //given

        //when then
        mockMvc.perform(get("/recipe/" + SAMPLE_ID + "/image"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipeId"))
                .andExpect(MockMvcResultMatchers.view().name("/recipe/imageform"));

    }

    @Test
    public void testHandleImagePost() throws Exception {
        //given
        MultipartFile mockMultipartFile = new MockMultipartFile(
                "imageFile",
                "test.text",
                "text/plain",
                "This is a sample content".getBytes());

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/recipe/" + SAMPLE_ID + "/image")
                .file((MockMultipartFile) mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/recipe/" + SAMPLE_ID + "/details"))
        ;

        Mockito.verify(imageService, Mockito.times(1)).saveImageFile(anyLong(), any());

    }

    @Test
    public void testRenderImageFromDb() throws Exception {
        //given
        MultipartFile mockMultipartFile = new MockMultipartFile(
                "imageFile",
                "test.text",
                "text/plain",
                "This is a sample content".getBytes());

        byte[] multipartFileBytes = mockMultipartFile.getBytes();
        int length = multipartFileBytes.length;
        Byte[] bytes = new Byte[length];

        for (int i = 0; i < length; i++) {
            bytes[i] = multipartFileBytes[i];
        }

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(SAMPLE_ID);
        recipeCommand.setImage(bytes);

        Mockito.when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/recipe/" + SAMPLE_ID + "/recipe-image"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        //then
        Assert.assertEquals(multipartFileBytes.length, response.getContentAsByteArray().length);


    }
}
