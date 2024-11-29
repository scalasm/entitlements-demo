package me.marioscalas.edemo.account;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_relationships")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account source;

    @ManyToOne
    @JoinColumn(name = "target_account_id")
    private Account target;

    @Enumerated(EnumType.STRING)
    private AccountRelationshipType type;
}
