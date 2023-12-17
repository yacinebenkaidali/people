package com.checkconsulting.person.features.person;

import java.util.List;

import com.checkconsulting.person.features.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private int age;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Product> products;
}
