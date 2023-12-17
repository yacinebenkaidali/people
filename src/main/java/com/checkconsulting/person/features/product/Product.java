package com.checkconsulting.person.features.product;

import com.checkconsulting.person.features.person.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private int id;
    private Category category;
    private double price;
    private String name;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    Person person;

}
