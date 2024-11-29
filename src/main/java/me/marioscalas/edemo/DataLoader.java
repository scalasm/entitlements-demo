package me.marioscalas.edemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import me.marioscalas.edemo.account.Account;
import me.marioscalas.edemo.account.AccountRepository;
import me.marioscalas.edemo.account.AccountType;
import me.marioscalas.edemo.account.OrganizationService;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;

    private final OrganizationService organizationService;

    @Override
    public void run(String... args) {
        final var district = organizationService.createDistrict("District 1");

        for (int i = 0; i < 2; i++) {
            final var school = organizationService.createSchool(district.getId(), "School " + i);
        }

        // for (int i = 0; i < 10; i++) {
        //     final var classAccount = accountRepository.save(
        //         Account.builder().name("Class " + i).type(AccountType.CLASS).build()
        //     );
        // }

        // for (int i = 0; i < 5; i++) {
        //     final var customGroupAccount = accountRepository.save(
        //         Account.builder().name("Custom Group " + i).type(AccountType.GROUP).build()
        //     );
        // }

        // for (int i = 0; i < 100; i++) {
        //     final var studentAccount = accountRepository.save(
        //         Account.builder().name("Student " + i).type(AccountType.INDIVIDUAL).build()
        //     );
        // }
    }
}
