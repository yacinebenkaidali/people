package com.checkconsulting.person.features.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.checkconsulting.person.features.person.PersonRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final PersonRepository personRepository;

    public ProductService(ProductRepository productRepository, PersonRepository personRepository) {
        this.productRepository = productRepository;
        this.personRepository = personRepository;
    }

    public Product createProduct(ProductDto productDto) {
        var person = this.personRepository.findById(productDto.getPersonId());
        if (person.isPresent()) {
            var product = new Product();
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setPerson(person.get());
            product.setName(productDto.getName());
            this.productRepository.save(product);
            return product;
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }
}
