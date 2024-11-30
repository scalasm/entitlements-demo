package me.marioscalas.edemo.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityManager;

@DataJpaTest(properties = {
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private EntityManager entityManager;

    private Product product;
    private Feature feature1;
    private Feature feature2;

    @BeforeEach
    public void setUp() {
        product = Product.builder().id("product-1").name("Product 1").build();
        feature1 = Feature.builder().id("feature-1").name("Feature 1").build();
        feature2 = Feature.builder().id("feature-2").name("Feature 2").build();

        productRepository.save(product);
        featureRepository.saveAll(List.of(feature1, feature2));
    }
}
