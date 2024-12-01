package me.marioscalas.edemo.product;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_memberships")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMembership {
    @EmbeddedId
    private ProductMembershipId id;
}
