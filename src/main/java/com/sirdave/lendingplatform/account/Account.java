package com.sirdave.lendingplatform.account;

import com.sirdave.lendingplatform.user.User;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private int id;

    private Double currentAmount;
    private Double maxLoanCredit;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
