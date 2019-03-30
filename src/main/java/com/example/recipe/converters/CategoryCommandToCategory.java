package com.example.recipe.converters;

import com.example.recipe.commands.CategoryCommand;
import com.example.recipe.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand != null) {
            final Category category = new Category();
            category.setId(categoryCommand.getId());
            category.setName(categoryCommand.getName());
            return category;
        }
        return null;
    }
}
