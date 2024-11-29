package me.marioscalas.edemo.account;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    private final AccountRepository accountRepository;
    
    public OrganizationServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createDistrict(String name) {
        return accountRepository.save(Account.builder().name(name).type(AccountType.DISTRICT).build());
    }

    @Override
    public Account createSchool(String districtId, String schoolName) {
        final var districtAccount = accountRepository.findById(districtId).orElseThrow( () -> new IllegalArgumentException("District not found"));
            
        final var schoolAccount = Account.builder().name(schoolName).type(AccountType.SCHOOL).build();
        schoolAccount.setRelationships(List.of(
            AccountRelationship.builder()
                .source(districtAccount)
                .target(schoolAccount)
                .type(AccountRelationshipType.IS_PART_OF)
                .build()
            )
        );

        return accountRepository.save(
            schoolAccount
        );
   }
}
