package com.checkconsulting.person.features.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping()
    public ProductResponse postMethodName(@RequestBody ProductDto productDto) {
        var product = this.productService.createProduct(productDto);

        var productResponse = ProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory()).build();

        var person = productResponse.new Person();
        person.setAge(product.getPerson().getAge());
        person.setEmail(product.getPerson().getEmail());
        person.setFirstName(product.getPerson().getFirstName());
        person.setLastName(product.getPerson().getLastName());

        productResponse.setPerson(person);
        return productResponse;
    }

}
