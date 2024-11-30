package me.marioscalas.edemo.account;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    private final AccountRepository accountRepository;
    private final AccountRelationshipRepository accountRelationshipRepository;    

    public OrganizationServiceImpl(AccountRepository accountRepository, AccountRelationshipRepository AccountRelationshipRepository) {
        this.accountRepository = accountRepository;
        this.accountRelationshipRepository = AccountRelationshipRepository;
    }

    @Override
    public Account createDistrict(String name) {
        return accountRepository.save(Account.builder().name(name).type(AccountType.DISTRICT).build());
    }

    @Override
    public Account createSchool(String districtId, String schoolName) {
        final var districtAccount = accountRepository.findByIdAndType(districtId, AccountType.DISTRICT).orElseThrow( () -> new IllegalArgumentException("District not found"));
            
        final var schoolAccount = Account.builder().name(schoolName).type(AccountType.SCHOOL).build();
        
        districtAccount.getRelationships().add(
            AccountRelationship.builder()
                .source(districtAccount)
                .target(schoolAccount)
                .type(AccountRelationshipType.HAS)
                .build()
        );

        final var saved = accountRepository.save(schoolAccount);
        accountRepository.save(districtAccount);
        return saved;
   }

    @Override
    public Account createClass(String schoolId, String className) {
        final var schoolAccount = accountRepository.findByIdAndType(schoolId, AccountType.SCHOOL).orElseThrow( () -> new IllegalArgumentException("School not found"));
            
        final var classAccount = Account.builder().name(className).type(AccountType.CLASS).build();
        
        schoolAccount.getRelationships().add(
            AccountRelationship.builder()
                .source(schoolAccount)
                .target(classAccount)
                .type(AccountRelationshipType.HAS)
                .build()
        );

        final var saved = accountRepository.save(classAccount);
        accountRepository.save(schoolAccount);
        return saved;
    }

    @Override
    public void relate(String sourceAccountId, String targetAccountId, AccountRelationshipType type) {
        final var sourceAccount = accountRepository.findById(sourceAccountId).orElseThrow( () -> new IllegalArgumentException("Source account not found: " + sourceAccountId));
        final var targetAccount = accountRepository.findById(targetAccountId).orElseThrow( () -> new IllegalArgumentException("Target account not found: " + targetAccountId));

        relate(sourceAccount, targetAccount, type);
    }

    @Override
    public void relate(Account sourceAccount, Account targetAccount, AccountRelationshipType type) {
        // TODO implement consistency checks (e.g., a class can be created in a school, a school in a district, etc.)

        sourceAccount.getRelationships().add(
            AccountRelationship.builder()
                .source(sourceAccount)
                .target(targetAccount)
                .type(type)
                .build()
        );

        accountRepository.save(sourceAccount);
    }


    @Override
    public Account createStudent(String studentName) {
        final var studentAccount = Account.builder().name(studentName).type(AccountType.INDIVIDUAL).build();
        return accountRepository.save(studentAccount);
    }

    @Override
    public void addStudentToClass(String studentId, String classId) {
        final var studentAccount = accountRepository.findByIdAndType(studentId, AccountType.INDIVIDUAL).orElseThrow( () -> new IllegalArgumentException("Student not found: " + studentId));
        final var classAccount = accountRepository.findByIdAndType(classId, AccountType.CLASS).orElseThrow( () -> new IllegalArgumentException("Student not found: " + classId));

        // Make the student part of the class
        studentAccount.getRelationships().add(
            AccountRelationship.builder()
                .source(studentAccount)
                .target(classAccount)
                .type(AccountRelationshipType.ENROLLED_IN)
                .build()
        );

        // Make the student part of the school
        final var relationshipsTargergetingThisClass = accountRelationshipRepository.findByTargetIdAndType(classId, AccountRelationshipType.HAS);
        Assert.isTrue(relationshipsTargergetingThisClass.size() == 1, "Inconsistent organization structure: this class should only belong to one school!");
        final var schoolAccount = relationshipsTargergetingThisClass.get(0).getSource();

        relate(studentAccount, schoolAccount, AccountRelationshipType.ENROLLED_IN);
    }
}
