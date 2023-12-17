package com.checkconsulting.person.features.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponse {
    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private int nbBoughtProducts;
    private String products;
}
