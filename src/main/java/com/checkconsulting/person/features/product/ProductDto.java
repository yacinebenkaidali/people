package com.checkconsulting.person.features.product;

import com.checkconsulting.person.validators.ValueOfEnum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductDto {
    @ValueOfEnum(enumClass = Category.class)
    private Category category;
    @NotNull
    @Min(0)
    private double price;
    @NotBlank
    private String name;

    @NotBlank
    private String personId;
}
