package com.example.recipe.services;

import com.example.recipe.commands.RecipeCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Created by pooya on 4/5/2019.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceImplIT {


    public static final Long SAMPLE_ID = 1L;
    public static final String SAMPLE_DESCRIPTION = "This is a sample description.";
    @Autowired
    private RecipeService recipeService;

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void saveCommand() throws Exception {

        //given
        RecipeCommand sampleRecipeCommand = new RecipeCommand();
        sampleRecipeCommand.setId(SAMPLE_ID);
        sampleRecipeCommand.setDescription(SAMPLE_DESCRIPTION);


        RecipeCommand expectedRecipeCommand = new RecipeCommand();
        expectedRecipeCommand.setId(SAMPLE_ID);
        expectedRecipeCommand.setDescription(SAMPLE_DESCRIPTION);


        //when
        RecipeCommand savedCommand = recipeService.saveCommand(sampleRecipeCommand);


        //then
        assertNotNull(savedCommand);
        assertEquals(SAMPLE_ID, savedCommand.getId());
        assertEquals(SAMPLE_DESCRIPTION, savedCommand.getDescription());


    }

}