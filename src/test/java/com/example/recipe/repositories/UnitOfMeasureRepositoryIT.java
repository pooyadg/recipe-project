package com.example.recipe.repositories;

import com.example.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by pooya on 3/29/2019.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {


    }

    @Test
//    @DirtiesContext
    public void testFindByUnit() throws Exception {

        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByUnit("Teaspoon");


        assertEquals("Teaspoon", teaspoon.get().getUnit());

    }

    @Test
    public void testFindByUnitForCup() throws Exception {

        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByUnit("Cup");


        assertEquals("Cup", teaspoon.get().getUnit());

    }

}