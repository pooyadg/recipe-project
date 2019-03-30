package com.example.recipe.converters;

import com.example.recipe.commands.UnitOfMeasureCommand;
import com.example.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void convertTest() {
        //given
        Long sampleId = 1L;
        String sampleUnit = "Spoon";
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand(sampleId, sampleUnit);
        UnitOfMeasure expectedUnitOfMeasure = new UnitOfMeasure();
        expectedUnitOfMeasure.setId(sampleId);
        expectedUnitOfMeasure.setUnit(sampleUnit);

        //when
        UnitOfMeasure unitOfMeasure = converter.convert(unitOfMeasureCommand);

        //then
        assertNotNull(unitOfMeasure);
        assertEquals(expectedUnitOfMeasure, unitOfMeasure);

    }
}