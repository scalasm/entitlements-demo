package me.marioscalas.edemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.marioscalas.edemo.account.AccountRelationshipType;
import me.marioscalas.edemo.account.AccountRepository;
import me.marioscalas.edemo.account.OrganizationService;
import me.marioscalas.edemo.product.Feature;
import me.marioscalas.edemo.product.Product;
import me.marioscalas.edemo.product.ProductService;

@Component
@RequiredArgsConstructor
@Transactional
public class DataLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;

    private final OrganizationService organizationService;
    private final ProductService productService;

    @Override
    public void run(String... args) {
//        createOrganization();
        createProducts();
    }
 
    @Transactional
    public void createProducts() {
        var allFeatures = productService.createFeatures(List.of(
            Feature.builder().id("feature-1").name("Feature 1").build(),
            Feature.builder().id("feature-2").name("Feature 2").build(),
            Feature.builder().id("feature-3").name("Feature 3").build(),
            Feature.builder().id("feature-4").name("Feature 4").build(),
            Feature.builder().id("feature-5").name("Feature 5").build(),
            Feature.builder().id("feature-6").name("Feature 6").build()
        ));

        final var product1 = productService.createProduct(
            Product.builder().id("product-1").name("Product 1")
            .features( allFeatures.subList(0, 3) )
            .build()
        );

        final var product2 = productService.createProduct(
            Product.builder().id("product-2").name("Product 2")
            .features( allFeatures.subList(2, 4) )
            .build()
        );

    }
        
    private void createOrganization() {
        // Create a district
        final var district = organizationService.createDistrict("District 1");

        // Create a bunch of students
        final List<String> studentIds = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            final var studentAccount = organizationService.createStudent("Student " + (i + 1));
            studentIds.add(studentAccount.getId());
            organizationService.relate(studentAccount.getId(), district.getId(), AccountRelationshipType.ENROLLED_IN);  
        }

        // Create schools, and assign some classes for each school
        final List<String> classIds = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            final var school = organizationService.createSchool(district.getId(), "School " + (i + 1));

            for (int j = 0; j < 5; j++) {
                final var classAccount = organizationService.createClass(school.getId(), String.format("Class %d-%d", i + 1, j + 1));
                classIds.add(classAccount.getId());
            }
        }

        for (int i = 0; i < studentIds.size(); i++) {
            int classIndex = i % classIds.size();
            organizationService.addStudentToClass(studentIds.get(i), classIds.get(classIndex));
        }
    }
}
