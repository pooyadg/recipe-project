package com.example.recipe.commands;

import lombok.Data;

@Data
public class UnitOfMeasureCommand {
    private Long id;
    private String unit;

    public UnitOfMeasureCommand() {
    }

    public UnitOfMeasureCommand(Long id, String unit) {
        this.id = id;
        this.unit = unit;
    }
}
