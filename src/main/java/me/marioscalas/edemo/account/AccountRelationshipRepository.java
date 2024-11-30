package me.marioscalas.edemo.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface AccountRelationshipRepository extends JpaRepository<AccountRelationship, Long>  {
    List<AccountRelationship> findByTargetIdAndType(String targetId, AccountRelationshipType type);    
}
