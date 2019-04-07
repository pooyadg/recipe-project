package com.example.recipe.commands;

import com.example.recipe.domain.UnitOfMeasure;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngredientCommand {

    private Long id;

    private Long recipeId;

    private String description;

    private UnitOfMeasureCommand unitOfMeasure;

    private BigDecimal amount;
}
