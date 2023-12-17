package com.checkconsulting.person.features.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Category category;
    private double price;
    private String name;
    private Person person;

    @Data
    @NoArgsConstructor
    class Person {
        private String email;
        private String firstName;
        private String lastName;
        private int age;
    }
}
