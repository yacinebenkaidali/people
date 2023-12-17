package com.checkconsulting.person.features.person;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class PersonUpdateDto {

    private String firstName;
    private String lastName;
    @Min(1)
    private int age;
}
