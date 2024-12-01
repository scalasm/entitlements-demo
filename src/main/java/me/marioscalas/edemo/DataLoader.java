package me.marioscalas.edemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.marioscalas.edemo.account.AccountRelationshipType;
import me.marioscalas.edemo.account.OrganizationService;
import me.marioscalas.edemo.product.Feature;
import me.marioscalas.edemo.product.Product;
import me.marioscalas.edemo.product.ProductBundleService;
import me.marioscalas.edemo.product.ProductService;
import me.marioscalas.edemo.product.ProgramCodes;
import me.marioscalas.edemo.product.ProgramService;
import me.marioscalas.edemo.product.ProductBundleService.ConstituentPart;

@Component
@RequiredArgsConstructor
@Transactional
public class DataLoader implements CommandLineRunner {

    private final OrganizationService organizationService;
    private final ProductService productService;
    private final ProgramService programService;
    private final ProductBundleService productBundleService;

    @Override
    public void run(String... args) {
//        createOrganization();
        createProducts();
    }
 
    @Transactional
    public void createProducts() {
        var allFeatures = productService.createFeatures(List.of(
            Feature.builder().id("feature-1").name("Math - Additions").build(),
            Feature.builder().id("feature-2").name("Math - Subtractions").build(),
            Feature.builder().id("feature-3").name("Math - Divisions").build(),
            Feature.builder().id("feature-4").name("Math - Multiplication").build(),
            Feature.builder().id("feature-5").name("Read - Basics").build(),
            Feature.builder().id("feature-6").name("Read - Advanced").build()
        ));

        // Create some sample products 
        productService.createProduct(
            Product.builder().id("basic-math").name("Basic Math")
            .features( allFeatures.subList(0, 4) )
            .build()
        );

        productService.createProduct(
            Product.builder().id("basic-reading").name("Elementary Reading")
            .features(List.of( allFeatures.get(4)))
            .build()
        );

        productService.createProduct(
            Product.builder().id("adv-reading").name("Advanced Reading")
            .features(List.of( allFeatures.get(5)))
            .build()
        );

        // Ensure we have some well-known programs
        programService.createProgram(ProgramCodes.DEFAULT_PROGRAM_CODE, "Default program");
        programService.createProgram(ProgramCodes.MATH_PROGRAM, "Math");
        programService.createProgram(ProgramCodes.EN_READING_PROGRAM, "Reading (English)");
    
        productBundleService.createProductBundle("ACME Cool Math for Kids", "Cool Math stuff for brilliant kids", List.of(
            new ConstituentPart(ProgramCodes.MATH_PROGRAM, "basic-math")
        ));

        productBundleService.createProductBundle("ACME Reading starter kit", "Reading starter kit for kids", List.of(
            new ConstituentPart(ProgramCodes.EN_READING_PROGRAM, "basic-reading")
        ));

        productBundleService.createProductBundle("ACME Reading Advanced kit", "Reading kit for advanced kids", List.of(
            new ConstituentPart(ProgramCodes.EN_READING_PROGRAM, "adv-reading")
        ));

        productBundleService.createProductBundle("ACME All-in-one Reading kit", "Reading kit for all kinds of kids", List.of(
            new ConstituentPart(ProgramCodes.EN_READING_PROGRAM, "basic-reading"),
            new ConstituentPart(ProgramCodes.EN_READING_PROGRAM, "adv-reading")
        ));
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
