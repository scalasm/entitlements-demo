package me.marioscalas.edemo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface FeatureSetRepository extends JpaRepository<FeatureSet, String> {
}
