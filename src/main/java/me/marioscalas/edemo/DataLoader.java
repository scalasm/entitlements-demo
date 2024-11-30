package me.marioscalas.edemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import me.marioscalas.edemo.account.AccountRelationshipType;
import me.marioscalas.edemo.account.AccountRepository;
import me.marioscalas.edemo.account.OrganizationService;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;

    private final OrganizationService organizationService;

    @Override
    public void run(String... args) {
        // Create a district
        final var district = organizationService.createDistrict("District 1");

        // Create a bunch of students
        final List<String> studentIds = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            final var studentAccount = organizationService.createStudent("Student " + i + 1);
            studentIds.add(studentAccount.getId());
            organizationService.relate(studentAccount.getId(), district.getId(), AccountRelationshipType.ENROLLED_IN);  
        }

        // Create a schools, and assign some classes for each school
        final List<String> classIds = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            final var school = organizationService.createSchool(district.getId(), "School " + i + 1);

            for (int j = 0; j < 5; j++) {
                final var classAccount = organizationService.createClass(school.getId(), String.format("Class %d-%s", i + 1, j + 1));

                classIds.add(classAccount.getId());
            }
        }

        for (int i = 0; i < studentIds.size(); i++) {
            int classIndex = i % classIds.size();

            organizationService.addStudentToClass(studentIds.get(i), classIds.get(classIndex));
        }
    }
}
