package com.example.recipe.converters;

import com.example.recipe.commands.CategoryCommand;
import com.example.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryToCategoryCommandTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String NAME = "descript";
    private CategoryToCategoryCommand convter;

    @Before
    public void setUp() throws Exception {
        convter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new Category()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setName(NAME);

        //when
        CategoryCommand categoryCommand = convter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(NAME, categoryCommand.getName());

    }

}