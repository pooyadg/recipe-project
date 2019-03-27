package com.example.recipe.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by pooya on 3/18/2019.
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String notes;


}
