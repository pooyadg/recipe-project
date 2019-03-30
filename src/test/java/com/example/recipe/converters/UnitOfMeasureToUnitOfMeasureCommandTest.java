package com.example.recipe.converters;

import com.example.recipe.commands.UnitOfMeasureCommand;
import com.example.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void convertTest() {
        //given
        Long sampleId = 1L;
        String sampleUnit = "Spoon";
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(sampleId);
        unitOfMeasure.setUnit(sampleUnit);

        UnitOfMeasureCommand expectedCommand = new UnitOfMeasureCommand();
        expectedCommand.setId(sampleId);
        expectedCommand.setUnit(sampleUnit);

        //when
        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        //then
        assertNotNull(unitOfMeasureCommand);
        assertEquals(expectedCommand, unitOfMeasureCommand);


    }
}