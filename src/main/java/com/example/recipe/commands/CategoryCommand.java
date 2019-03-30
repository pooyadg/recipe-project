package com.example.recipe.commands;


import com.example.recipe.domain.Recipe;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryCommand {
    private Long id;
    private String name;

    public CategoryCommand() {
    }

    public CategoryCommand(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
