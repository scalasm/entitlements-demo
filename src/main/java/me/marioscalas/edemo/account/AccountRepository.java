package me.marioscalas.edemo.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;


@GraphQlRepository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByIdAndType(String id, AccountType type);
}