package com.example.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by pooya on 3/18/2019.
 */

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Recipe recipe;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private UnitOfMeasure unitOfMeasure;

    private BigDecimal amount;


}
